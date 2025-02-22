from __future__ import print_function, division

import cv2
import timm
import torch
import torch.nn as nn
import torch.optim as optim
import torchvision
from PIL import Image
from torch.optim import lr_scheduler
import numpy as np
from torchvision import datasets, models, transforms
import matplotlib.pyplot as plt
import time
import os
import copy

from labelsmoothing2 import LabelSmoothingCrossEntropy

from albumentations import (
   HorizontalFlip, IAAPerspective, ShiftScaleRotate, CLAHE, RandomRotate90,
  Transpose, ShiftScaleRotate, Blur, OpticalDistortion, GridDistortion, HueSaturationValue,
 IAAAdditiveGaussianNoise, GaussNoise, MotionBlur, MedianBlur, IAAPiecewiseAffine,
 IAASharpen, IAAEmboss, RandomBrightnessContrast, Flip, OneOf, Compose,Cutout)
plt.ion()

# load data
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
data_dir = "smalldata"
img_size = 224
batch_size = 32
outdir = 'test'
checkpointdir = 'checkpoint'
modestatedir = 'modestate'

data_transforms = \
    {'train': transforms.Compose([
        transforms.RandomResizedCrop(img_size),
        transforms.RandomHorizontalFlip(p=0.3),
        transforms.RandomVerticalFlip(p=0.3),
        transforms.RandomRotation(90),
        HueSaturationValue(hue_shift_limit=0.2, sat_shift_limit=0.2, val_shift_limit=0.2, p=0.5),
        RandomBrightnessContrast(brightness_limit=(-0.1,0.1), contrast_limit=(-0.1, 0.1), p=0.5),
        ShiftScaleRotate(p=0.5),
        Cutout(p=0.5),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225]),

        # Cutout(n_holes=1, length=32)

    ]),
        'val': transforms.Compose([
            transforms.Resize(img_size),
            transforms.CenterCrop(img_size),
            transforms.ToTensor(),
            transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
        ]),
    }


class CassavaDataset(torch.utils.data.Dataset):
    """
    Helper Class to create the pytorch dataset
    """

    def __init__(self, df, data_path=data_dir, mode="train", transforms=None):
        super().__init__()
        self.df_data = df.values
        self.data_path = data_path
        self.transforms = transforms
        self.mode = mode
        self.data_dir = "train_images" if mode == "train" else "test_images"

    def __len__(self):
        return len(self.df_data)

    def __getitem__(self, index):
        img_name, label = self.df_data[index]
        img_path = os.path.join(self.data_path, self.data_dir, img_name)
        img = Image.open(img_path).convert("RGB")

        if self.transforms is not None:
            image = self.transforms(img)

        return image, label

image_datasets['train'] = x: CassavaDataset(os.path.join(data_dir, x),data_transforms[x])


dataloaders = {x: torch.utils.data.DataLoader(image_datasets[x], batch_size=batch_size,
                                              shuffle=True, num_workers=4)
               for x in ['train', 'val']}

dataset_sizes = {x: len(image_datasets[x]) for x in ['train', 'val']}

class_names = image_datasets['train'].classes


def train_model(model, criterion, optimizer, scheduler, num_epochs, start_epoch, best_acc):
    # 设置测试损失list和测试acc 列表
    val_loss_list = []
    val_acc_list = []
    # 设置训练损失list和训练acc 列表
    train_loss_list = []
    train_acc_list = []

    since = time.time()

    best_model_wts = copy.deepcopy(model.state_dict())

    for epoch in range(num_epochs):
        print('Epoch {}/{}'.format(epoch, num_epochs - 1))
        print('-' * 10)

        # Each epoch has a training and validation phase
        for phase in ['train', 'val']:
            if phase == 'train':
                model.train()  # Set model to training mode
            else:
                model.eval()  # Set model to evaluate mode

            running_loss = 0.0
            running_corrects = 0

            # Iterate over data.
            for inputs, labels in dataloaders[phase]:
                inputs = inputs.to(device)
                labels = labels.to(device)

                # zero the parameter gradients
                optimizer.zero_grad()

                # forward
                # track history if only in train
                with torch.set_grad_enabled(phase == 'train'):
                    outputs = model(inputs)
                    _, preds = torch.max(outputs, 1)
                    loss = criterion(outputs, labels)

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
                np.savetxt("train_acc_list.txt", train_acc_list)

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
    checkpoint = {
        'best_acc': best_acc,
        'epoch': epoch + 1 + start_epoch,
        'model': model.state_dict(),
        'optimizer': optimizer.state_dict(),
    }
    torch.save(model, outdir)
    torch.save(checkpoint, checkpointdir)
    torch.save(model.state_dict(), modestatedir)
    # load best model weights
    model.load_state_dict(best_model_wts)
    return model


if __name__ == '__main__':
    # optimizer = AdaBelief(model.parameters(),lr=0.05,eps=1e-12,betas=(0.9, 0.999)) scheduler =
    # torch.optim.lr_scheduler.CosineAnnealingWarmRestarts(optimizer,T_0=5,T_mult=2,eta_min=1e-5,last_epoch=-1)
    # if use vgg19 open this
    # model_ft = models.vgg19(pretrained=True)
    # for param in model_ft.parameters():
    #    param.requires_grad = False
    # model_ft.classifier = torch.nn.Sequential(torch.nn.Linear(25088, 4096),
    #                                         torch.nn.ReLU(),
    #                                        torch.nn.Dropout(p=0.5),
    #                                       torch.nn.Linear(4096, 4096),
    #                                      torch.nn.ReLU(),
    #                                     torch.nn.Dropout(p=0.5),
    #                                    torch.nn.Linear(4096, 5))

    # Observe that all parameters are being optimized original
    # optimizer_ft = optim.SGD(model_ft.parameters(), lr=0.001, momentum=0.9)

    # optimizer = torch.optim.Adam(model_ft.parameters(), lr=1e-4, weight_decay=1e-6)
    # scheduler = torch.optim.lr_scheduler.CosineAnnealingWarmRestarts(optimizer, T_0=10, T_mult=1, eta_min=1e-6,
    #                                                                last_epoch=-1)

    # for batch_idx, (inputs, targets) in enumerate(dataloaders['train']):
    #    inputs, targets = inputs.to(device), targets.to(device)
    #    inputs, targets_a, targets_b, lam = mixup_data(inputs, targets,
    #                                                   1.)
    #
    #     optimizer.zero_grad()
    #    outputs = model_ft(inputs)
    #   loss = mixup_criterion(criterion, outputs, targets_a, targets_b, lam)
    #  loss.backward()
    # optimizer.step()
    # print('mixupdata finished')
    print("train_number:", len(image_datasets['train']))
    print("val_number:", len(image_datasets['val']))
    print("train loader:", len(dataloaders['train']))
    print("val loader:", len(dataloaders['val']))
    # net_name = 'efficientnet-b0'
    # model = EfficientNet.from_name(net_name)
    # num_ftrs = model._fc.in_features
    # model._fc = nn.Linear(num_ftrs, class_num)
    class_num = 5
    MODEL_PATH = ("models/jx_vit_base_p16_224-80ecf9dd.pth")
    model = timm.create_model("vit_base_patch16_224", pretrained=True)
    model.load_state_dict(torch.load(MODEL_PATH))
    model.head = nn.Linear(model.head.in_features, class_num)
    # momentum = 0.9
    lr = 1e-4
    # optimizer = optim.SGD((model.parameters()), lr=lr,
    #                      momentum=momentum, weight_decay=0.0004)
    model = model.to(device)
    # 初始损失函数
    # criterion = nn.CrossEntropyLoss()
    # criterion = criterion.to(device)
    criterion = LabelSmoothingCrossEntropy()
    criterion = criterion.to(device)

    optimizer = optim.Adam(params=model.parameters(), lr=lr,eps=1e-12,betas=(0.9, 0.999))
    # exp_lr_scheduler = lr_scheduler.StepLR(optimizer, step_size=10, gamma=0.1)
    scheduler = torch.optim.lr_scheduler.CosineAnnealingWarmRestarts(optimizer, T_0=10, T_mult=1,
                                                                     eta_min=1e-4, last_epoch=-1)

    num_epoch = 1
    resume = False
    if resume:  # resume为参数，第一次训练时设为0，中断再训练时设为1
        checkpoint = torch.load('checkpoint')
        best_acc = checkpoint['best_acc']
        start_epoch = checkpoint['epoch']
        model.load_state_dict(checkpoint['model'])
        optimizer.load_state_dict(checkpoint['optimizer'])
        print('Load checkpoint at epoch {}.'.format(start_epoch))
        print('Best accuracy so far {}.'.format(best_acc))
        model_ft = train_model(model, criterion, optimizer, scheduler,
                               num_epochs=num_epoch, start_epoch=start_epoch, best_acc=best_acc)
    else:
        start_epoch = 0
        best_acc = 0.0
        model_ft = train_model(model, criterion, optimizer, scheduler,
                               num_epochs=num_epoch, start_epoch=start_epoch, best_acc=best_acc)
