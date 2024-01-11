import cv2
import matplotlib.pyplot as plt

img=cv2.imread('D:/workspace/wokr/clc2/train/CGM/brightnessE2.jpg')
img = cv2.resize(img, (224, 224))
plt.imshow(img)
plt.show()