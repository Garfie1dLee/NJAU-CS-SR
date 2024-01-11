import os
from PIL import Image
import matplotlib.pyplot as plt


img1 = Image.open(os.path.join('clc/train/Cassava Bacterial Blight (CBB)', '72925791.jpg'))
img2 = Image.open(os.path.join('clc/train/Cassava Bacterial Blight (CBB)', '404853146.jpg'))
img3 = Image.open(os.path.join('clc/train/Cassava Bacterial Blight (CBB)', '2198388199.jpg'))
img4 = Image.open(os.path.join('clc/train/Cassava Bacterial Blight (CBB)', '2497160011.jpg'))
img5 = Image.open(os.path.join('clc/train/Cassava Bacterial Blight (CBB)', '1439675422.jpg'))
img6 = Image.open(os.path.join('clc/train/Cassava Bacterial Blight (CBB)', '1725598881.jpg'))




plt.figure("CBB",figsize=(10,5))
plt.suptitle('CBB')
plt.subplot(2,3,1), plt.title('id=72925791')
plt.imshow(img1), plt.axis('off')

plt.subplot(2,3,2), plt.title('id=404853146')
plt.imshow(img2), plt.axis('off')

plt.subplot(2,3,3), plt.title('id=2198388199')
plt.imshow(img3), plt.axis('off')

plt.subplot(2,3,4), plt.title('id=2497160011')
plt.imshow(img4), plt.axis('off')

plt.subplot(2,3,5), plt.title('id=1439675422')
plt.imshow(img5), plt.axis('off')

plt.subplot(2,3,6), plt.title('id=1725598881')
plt.imshow(img6), plt.axis('off')

plt.show()