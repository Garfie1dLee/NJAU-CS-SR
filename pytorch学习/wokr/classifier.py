import torch
from PIL import Image
from torchvision import transforms
from train import VGG16net

import time

name2label = {'empty': 0, 'occupied': 1}
device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
resize = 224
tf = transforms.Compose([                                               # 常用的数据变换器
					    lambda x:Image.open(x).convert('RGB'),          # string path= > image data
					                                                    # 这里开始读取了数据的内容了
					    transforms.Resize(                              # 数据预处理部分
					        (int(resize * 1.25), int(resize * 1.25))),
					    transforms.RandomRotation(15),
					    transforms.CenterCrop(resize),             # 防止旋转后边界出现黑框部分
					    transforms.ToTensor(),
					    transforms.Normalize(mean=[0.485, 0.456, 0.406],
					                        std=[0.229, 0.224, 0.225])
       					])


def prediect(img_path):
    model = VGG16net().to(device)
    model.load_state_dict(torch.load('model/model.pth'))
    # net = net.to(device)
    with torch.no_grad():
        img = tf(img_path).unsqueeze(0)
        img_ = img.to(device)
        start = time.time()
        outputs = model(img_)
        _, predicted = torch.max(outputs, 1)
        predicted_number = predicted[0].item()
        end = time.time()
        print('this picture maybe :',list(name2label.keys())[list(name2label.values()).index(predicted_number)])
        print('FPS:', 1/(end-start))
if __name__ == '__main__':
    prediect('./pre/empty/spot524.jpg')