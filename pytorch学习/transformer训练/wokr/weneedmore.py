import PIL.Image as Image
import os
from torchvision import transforms as transforms

outfile = 'outs'
im = Image.open('base/base.jpg')
im.save(os.path.join(outfile, 'test.jpg'))

new_im = transforms.Resize((100, 200))(im)
print(f'{im.size}---->{new_im.size}')
new_im.save(os.path.join(outfile, '1.jpg'))

new_im = transforms.RandomCrop(100)(im)   # 裁剪出100x100的区域
new_im.save(os.path.join(outfile, '2_1.jpg'))
new_im = transforms.CenterCrop(100)(im)
new_im.save(os.path.join(outfile, '2_2.jpg'))



new_im = transforms.RandomHorizontalFlip(p=1)(im)   # p表示概率
new_im.save(os.path.join(outfile, '3_1.jpg'))
new_im = transforms.RandomVerticalFlip(p=1)(im)
new_im.save(os.path.join(outfile, '3_2.jpg'))

new_im = transforms.RandomRotation(45)(im)    #随机旋转45度
new_im.save(os.path.join(outfile, '4.jpg'))

new_im = transforms.ColorJitter(brightness=1)(im)
new_im = transforms.ColorJitter(contrast=1)(im)
new_im = transforms.ColorJitter(saturation=0.5)(im)
new_im = transforms.ColorJitter(hue=0.5)(im)
new_im.save(os.path.join(outfile, '5_1.jpg'))

new_im = transforms.RandomGrayscale(p=0.5)(im)    # 以0.5的概率进行灰度化
new_im.save(os.path.join(outfile, '6_2.jpg'))

