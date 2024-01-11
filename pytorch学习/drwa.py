import numpy as np
import matplotlib.pyplot as plt
import pylab as pl
from mpl_toolkits.axes_grid1.inset_locator import inset_axes

def plt_image(x_input, y_input, title, xlabel, ylabel):
    plt.plot(x_input, y_input, linewidth=2)
    plt.title(title)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.show()

data1_loss =np.loadtxt("val_loss_list.txt")
data2_loss = np.loadtxt("train_loss_list.txt")
data3_acc = np.loadtxt("val_acc_list.txt")

epoches=range(0,100)
plt_image(epoches, data1_loss, 'valloss', 'Epochs', 'ValLoss')
plt_image(epoches, data2_loss, 'trainloss', 'Epochs', 'TrainLoss')
plt_image(epoches, data3_acc, 'valacc', 'Epochs', 'ValAcc')
plt.plot(epoches, data1_loss, linewidth=2)
plt.plot(epoches, data2_loss, linewidth=2)
plt.show()