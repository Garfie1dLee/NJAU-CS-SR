import shutil
import os


def classify_data(txt_path, labels):
    fh = open(txt_path, 'r', encoding='utf-8')
    lines = fh.readlines()

    for line in lines:
        line = line.strip('\n')  # 移除字符串首尾的换行符
        line = line.rstrip()  # 删除末尾空
        words = line.split()  # 以空格为分隔符 将字符串分成两部分
        imgs_name = words[0]  # imgs中包含有图像路径和标签
        srcfile = './' + imgs_name
        imgs_label = int(words[1])
        print(srcfile)
        if imgs_label == 0:
            shutil.copy(srcfile, './' + labels[0])
        elif imgs_label == 1:
            shutil.copy(srcfile, './' + labels[1])
        elif imgs_label == 2:
            shutil.copy(srcfile, './' + labels[2])
        elif imgs_label == 3:
            shutil.copy(srcfile, './' + labels[3])
        elif imgs_label == 4:
            shutil.copy(srcfile, './' + labels[4])
    print("Copy files Successfully!")


if __name__ == '__main__':
    # 创建文件夹
        label = ["Cassava Bacterial Blight (CBB)", "Cassava Brown Streak Disease (CBSD)",  "Cassava Green Mottle (CGM)",  "Cassava Mosaic Disease (CMD)", "Healthy"]
    for i in label:
        os.mkdir(i)

    classify_data('./label.txt', label)
