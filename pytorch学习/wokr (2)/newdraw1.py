import os
from PIL import Image
import matplotlib.pyplot as plt


img1 = Image.open(os.path.join('clc/train/Cassava Brown Streak Disease (CBSD)', '205418485.jpg'))
img2 = Image.open(os.path.join('clc/train/Cassava Brown Streak Disease (CBSD)', '487884059.jpg'))
img3 = Image.open(os.path.join('clc/train/Cassava Brown Streak Disease (CBSD)', '890852512.jpg'))
img4 = Image.open(os.path.join('clc/train/Cassava Brown Streak Disease (CBSD)', '1156464284.jpg'))
img5 = Image.open(os.path.join('clc/train/Cassava Brown Streak Disease (CBSD)', '1615919700.jpg'))
img6 = Image.open(os.path.join('clc/train/Cassava Brown Streak Disease (CBSD)', '4277201548.jpg'))




plt.figure("CBSD",figsize=(10,5))
plt.suptitle('CBSD')
plt.subplot(2,3,1), plt.title('id=205418485')
plt.imshow(img1), plt.axis('off')

plt.subplot(2,3,2), plt.title('id=487884059')
plt.imshow(img2), plt.axis('off')

plt.subplot(2,3,3), plt.title('id=890852512')
plt.imshow(img3), plt.axis('off')

plt.subplot(2,3,4), plt.title('id=1156464284')
plt.imshow(img4), plt.axis('off')

plt.subplot(2,3,5), plt.title('id=1615919700')
plt.imshow(img5), plt.axis('off')

plt.subplot(2,3,6), plt.title('id=4277201548')
plt.imshow(img6), plt.axis('off')

plt.show()