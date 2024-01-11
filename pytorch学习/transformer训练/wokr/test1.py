import os

import torch

from weneedmoreeee import createImage

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
data_dir=""
imageDir=os.path.join(data_dir, 'clc/val/Cassava Brown Streak Disease (CBSD)') #要改变的图片的路径文件夹
saveDir=os.path.join(data_dir, 'clc2/val/CBSD')   #数据增强生成图片的路径文件夹
createImage(imageDir,saveDir)

