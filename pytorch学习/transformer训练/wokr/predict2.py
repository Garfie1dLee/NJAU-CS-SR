import torch, glob, cv2
import numpy as np
import torch.nn as nn
import torch.nn.functional as F
from PIL import Image
from torchvision import models, transforms
import matplotlib.pyplot as plt


def preict_one_img(img_path, model_path):
    net = torch.load(model_path, map_location=torch.device('cpu'))
    img = Image.open(img_path)
    my_transforms = transforms.Compose([transforms.Resize((224,224)),

                                        transforms.ToTensor(),
                                        transforms.Normalize(
                                            [0.485, 0.456, 0.406],
                                            [0.229, 0.224, 0.225])])

    tensor = my_transforms(img)
    # 2.将数据变成网络需要的shape


    out1 = net(tensor)
    out1 = F.softmax(out1, dim=1)
    print(out1)
    proba, class_ind = torch.max(out1, 1)

    proba = float(proba)
    class_ind = int(class_ind)
    print(proba, class_ind)
    img = img.cpu().numpy().squeeze(0)
    # print(img.shape)
    plt.title("the predict is %s . prob is %s" % (classes[class_ind], round(proba, 3)))  # round(proba, 3)保留三位小数
    plt.show()


if __name__ == '__main__':
    classes = ["a", "b", "c", "d", "e"]
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    img_path = 'predict/3403726678.jpg'
    model_path = 'models/h10'
    preict_one_img(img_path, model_path)
