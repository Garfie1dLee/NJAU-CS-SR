import numpy as np
import re
import matplotlib.pyplot as plt


def plt_image(x_input, y_input, title, xlabel, ylabel):
    plt.plot(x_input, y_input, linewidth=2)
    plt.title(title)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.show()
val_loss_list = []
val_acc_list = []
train_loss_list = []
train_acc_list = []

filename='Data.txt'
f=open(filename)
with open(filename,'r') as file_to_read:
    while True:
        lines=file_to_read.readline()
        if not lines:
            break
            pass
        if 'train' in lines :
            numbers=re.findall(r"\d+\.?\d*", lines)
            print(numbers)
            train_loss_list.append(float(numbers[0]))
            train_acc_list.append(float(numbers[1]))
        if 'val' in lines :
            numbers = re.findall(r"\d+\.?\d*", lines)
            print(numbers)
            val_loss_list.append(float(numbers[0]))
            val_acc_list.append(float(numbers[1]))
f.close()


data1_loss =val_loss_list
data2_loss =train_loss_list
data3_acc = val_acc_list
data4_acc = train_acc_list

epoches=range(0,50)
plt_image(epoches, data1_loss, 'valloss', 'Epochs', 'ValLoss')
plt_image(epoches, data2_loss, 'trainloss', 'Epochs', 'TrainLoss')
plt_image(epoches, data3_acc, 'valacc', 'Epochs', 'ValAcc')
plt_image(epoches, data4_acc, 'trainacc', 'Epochs', 'TrainAcc')
plt.plot(epoches, data1_loss, linewidth=2)
plt.plot(epoches, data2_loss, linewidth=2)

#plt.plot(epoches, data3_acc, linewidth=2)
#plt.plot(epoches, data4_acc, linewidth=2)
plt.show()



