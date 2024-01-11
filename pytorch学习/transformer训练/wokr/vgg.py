from __future__ import print_function,division
import torch
import torchvision
import torch.nn as nn
import torch.optim as optim
from torch.optim import lr_scheduler
import numpy as np
from torch.autograd import Variable
from torchvision import datasets,models,transforms
import matplotlib.pyplot as plt
import time
import os
import copy
from typing import Dict, Any
import cv2
from torchvision.models import EfficientNet

from wokr.AdaBelief import AdaBelief
from wokr.Cutout import Cutout
from wokr.Mix import mixup_data, mixup_criterion

plt.ion()

#load data
data_transforms=\
    {'train': transforms.Compose([
        transforms.RandomResizedCrop(224),
        transforms.RandomHorizontalFlip(),
        transforms.RandomRotation(90),
        transforms.RandomVerticalFlip(),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225]),
        Cutout(n_holes=1, length=32)

    ]),
    'val': transforms.Compose([
        transforms.Resize(224),
        transforms.CenterCrop(224),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    ]),
    }
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
data_dir="smalldata"

image_datasets = {x: datasets.ImageFolder(os.path.join(data_dir, x),
                                          data_transforms[x])
                  for x in ['train', 'val']}

dataloaders = {x: torch.utils.data.DataLoader(image_datasets[x], batch_size=32,
                                             shuffle=True, num_workers=4)
                  for x in ['train', 'val']}


dataset_sizes = {x: len(image_datasets[x]) for x in ['train', 'val']}

class_names = image_datasets['train'].classes


def train_model(model, criterion, optimizer, scheduler, num_epochs):
    # 设置测试损失list和测试acc 列表
    val_loss_list = []
    val_acc_list = []
    # 设置训练损失list和训练acc 列表
    train_loss_list = []
    train_acc_list = []

    since = time.time()

    best_model_wts = copy.deepcopy(model.state_dict())
    best_acc = 0.0

    for epoch in range(num_epochs):
        print('Epoch {}/{}'.format(epoch, num_epochs - 1))
        print('-' * 10)

        # Each epoch has a training and validation phase
        for phase in ['train', 'val']:
            if phase == 'train':
                model.train()  # Set model to training mode
            else:
                model.eval()   # Set model to evaluate mode

            running_loss = 0.0
            running_corrects = 0

            # Iterate over data.
            for inputs, labels in dataloaders[phase]:
                inputs = inputs.to(device)
                labels = labels.to(device)
                inputs, targets_a, targets_b, lam = mixup_data(inputs, labels,
                                                               1.)
                # zero the parameter gradients
                optimizer.zero_grad()

                # forward
                # track history if only in train
                with torch.set_grad_enabled(phase == 'train'):
                    outputs = model(inputs)
                    _, preds = torch.max(outputs, 1)
                    loss = mixup_criterion(criterion, outputs, targets_a, targets_b, lam)

                    # backward + optimize only if in training phase
                    if phase == 'train':
                        loss.backward()
                        optimizer.step()

                # statistics
                running_loss += loss.item() * inputs.size(0)
                running_corrects += torch.sum(preds == labels.data)
            if phase == 'train':
                scheduler.step()

            epoch_loss = running_loss / dataset_sizes[phase]
            epoch_acc = running_corrects.double() / dataset_sizes[phase]
            
            if phase == 'train':
                train_loss_list.append(epoch_loss)
                train_acc_list.append(epoch_acc)
                np.savetxt("train_loss_list.txt", train_loss_list)
                np.savetxt("train_acc_list.txt",train_acc_list)

            if phase == 'val':
                val_loss_list.append(epoch_loss)
                val_acc_list.append(epoch_acc)
                np.savetxt("val_loss_list.txt", val_loss_list)
                np.savetxt("val_acc_list.txt", val_acc_list)

            print('{} Loss: {:.4f} Acc: {:.4f}'.format(
                phase, epoch_loss, epoch_acc))

            # deep copy the model
            if phase == 'val' and epoch_acc > best_acc:
                best_acc = epoch_acc
                best_model_wts = copy.deepcopy(model.state_dict())

        print()

    time_elapsed = time.time() - since
    print('Training complete in {:.0f}m {:.0f}s'.format(
        time_elapsed // 60, time_elapsed % 60))
    print('Best val Acc: {:4f}'.format(best_acc))
    print('Finished Training')
    torch.save(model, 'test')
    torch.save(model.state_dict(), 'test_state')
    # load best model weights
    model.load_state_dict(best_model_wts)
    return model


if __name__ == '__main__':
  print("train_number:", len(image_datasets['train']))
  print("val_number:", len(image_datasets['val']))
  print("train loader:", len(dataloaders['train']))
  print("val loader:", len(dataloaders['val']))

  model_ft=models.vgg19(pretrained=True)
  for param in model_ft.parameters():
      param.requires_grad = False
  model_ft.classifier = torch.nn.Sequential(torch.nn.Linear(25088, 4096),
                                         torch.nn.ReLU(),
                                         torch.nn.Dropout(p=0.5),
                                         torch.nn.Linear(4096, 4096),
                                         torch.nn.ReLU(),
                                         torch.nn.Dropout(p=0.5),
                                         torch.nn.Linear(4096, 5))

  model_ft = model_ft.to(device)
  criterion = nn.CrossEntropyLoss()
  optimizer = torch.optim.Adam(model_ft.parameters(), lr=1e-4, weight_decay=1e-6)
  scheduler = torch.optim.lr_scheduler.CosineAnnealingWarmRestarts(optimizer, T_0=10, T_mult=1, eta_min=1e-6,
                                                                    last_epoch=-1)

  model_ft = train_model(model_ft, criterion, optimizer, scheduler,
                       num_epochs=50)
