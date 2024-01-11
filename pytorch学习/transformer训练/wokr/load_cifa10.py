import os

import torch
from torchvision import transforms, datasets
from torch.utils.data import DataLoader
import argparse


def get_loader(args):
    # 设置数据加载时的变换形式，包括撞转成tensor,裁剪，归一化
    transform_train = transforms.Compose([
        transforms.RandomResizedCrop((args.img_size, args.img_size), scale=(0.05, 1.0)),
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.5, 0.5, 0.5], std=[0.5, 0.5, 0.5])
    ])
    transform_test = transforms.Compose([
        transforms.Resize((args.img_size, args.img_size)),
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.5, 0.5, 0.5], std=[0.5, 0.5, 0.5])
    ])
    trainset = datasets.ImageFolder(os.path.join('data', 'train'), transform_train)
    testset = datasets.ImageFolder(os.path.join('data', 'test'), transform_train)
    print("train number:", len(trainset))
    print("test number:", len(testset))
    train_loader = torch.utils.data.DataLoader(trainset, batch_size=args.train_batch_size, num_workers=8,
                                               pin_memory=True, shuffle=True)
    test_loader = DataLoader(testset, batch_size=args.eval_batch_size, num_workers=8, pin_memory=True, shuffle=False)
    print("train_loader:", len(train_loader))
    print("test_loader:", len(test_loader))
    return train_loader, test_loader

# 定义一个实例配置文件
#
# parser = argparse.ArgumentParser()
# parser.add_argument("--img_size", type=int, default=224, )
# parser.add_argument("--train_batch-size", default=16, type=int, )
# parser.add_argument("--eval_batch-size", default=16, type=int, )
# args = parser.parse_args()
# get_loader(args)
