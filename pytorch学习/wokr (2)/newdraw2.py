import os
from PIL import Image
import matplotlib.pyplot as plt


img1 = Image.open(os.path.join('clc/train/Healthy', '12351712.jpg'))
img2 = Image.open(os.path.join('clc/train/Healthy', '181653327.jpg'))
img3 = Image.open(os.path.join('clc/train/Healthy', '517170612.jpg'))
img4 = Image.open(os.path.join('clc/train/Healthy', '1300823152.jpg'))
img5 = Image.open(os.path.join('clc/train/Healthy', '2228488681.jpg'))
img6 = Image.open(os.path.join('clc/train/Healthy', '3001133912.jpg'))




plt.figure("Healthy",figsize=(10,5))
plt.suptitle('Healthy')
plt.subplot(2,3,1), plt.title('id=12351712')
plt.imshow(img1), plt.axis('off')

plt.subplot(2,3,2), plt.title('id=181653327')
plt.imshow(img2), plt.axis('off')

plt.subplot(2,3,3), plt.title('id=517170612')
plt.imshow(img3), plt.axis('off')

plt.subplot(2,3,4), plt.title('id=1300823152')
plt.imshow(img4), plt.axis('off')

plt.subplot(2,3,5), plt.title('id=2228488681')
plt.imshow(img5), plt.axis('off')

plt.subplot(2,3,6), plt.title('id=3001133912')
plt.imshow(img6), plt.axis('off')

plt.show()