import tkinter
from tkinter import filedialog
import torch
import cv2
import numpy
import matplotlib
from matplotlib.pyplot import subplots
from torchvision import transforms
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg


matplotlib.use('TkAgg')
device = torch.device("cpu")
model_path = 'models/h10'


def AskPicture():
    Picture_Path = tkinter.filedialog.askopenfilename()
    net = torch.load(model_path, map_location=device)
    net = net.to(device)
    torch.no_grad()
    net.eval()
    pic = cv2.imread(Picture_Path)
    pic = cv2.resize(pic, (224, 224))
    tran = transforms.ToTensor()
    tran1 = transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    pic = tran(pic)
    pic = tran1(pic)
    pic = pic.view(1, 3, 224, 224)
    pic = pic.to(device)
    outputs = net(pic)
    preds = torch.nn.functional.softmax(outputs, dim=1)
    pred = preds.detach().numpy()
    prob, preds = torch.max(preds, 1)
    toplevel = tkinter.Toplevel()
    toplevel.title('predict')
    toplevel.geometry('500x350')
    toplevel.configure(bg='white')
    toplevel.resizable(width=False, height=False)
    recipe = ['CBB', 'CBSD', 'CGM', 'CMD', 'Healthy']
    data = [pred[0][0], pred[0][1], pred[0][2], pred[0][3], pred[0][4]]
    prob = float(prob)
    preds = int(preds)
    result0 = "the predict is %s . prob is %s" % (recipe[preds], round(prob, 3))
    label = tkinter.Label(toplevel, text=result0, bg='white', bd=15)
    label.pack()
    fig, ax = subplots(figsize=(5, 3), subplot_kw=dict(aspect="equal"))
    canvas_spice = FigureCanvasTkAgg(fig, toplevel)
    canvas_spice.get_tk_widget().pack()
    wedges, texts = ax.pie(data, normalize=False, wedgeprops=dict(width=0.5), startangle=-40)
    bbox_props = dict(boxstyle="square,pad=0.3", fc="w", ec="k", lw=0.72)
    kw = dict(xycoords='data', textcoords='data', arrowprops=dict(arrowstyle="-"),
              bbox=bbox_props, zorder=0, va="center")
    for i, p in enumerate(wedges):
        ang = (p.theta2 - p.theta1) / 2. + p.theta1
        y = numpy.sin(numpy.deg2rad(ang))
        x = numpy.cos(numpy.deg2rad(ang))
        horizontalalignment = {-1: "right", 1: "left"}[int(numpy.sign(x))]
        connectionstyle = "angle,angleA=0,angleB={}".format(ang)
        kw["arrowprops"].update({"connectionstyle": connectionstyle})
        ax.annotate(recipe[i], size=15, xy=(x, y), xytext=(1.35 * numpy.sign(x), 1.4 * y),
                    horizontalalignment=horizontalalignment, **kw)
    ax.set_title("")
    canvas_spice.draw()


root = tkinter.Tk()
root.title('main')
root.geometry('200x50')
root.configure(bg='white')
root.resizable(width=False, height=False)

btn1 = tkinter.Button(root, text='ChoosePic', command=lambda: AskPicture())
btn1.place(relx=0.2, rely=0.1, relwidth=0.6, relheight=0.75)

root.mainloop()
