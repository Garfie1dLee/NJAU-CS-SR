import matplotlib.pyplot as plt
import numpy as np
def axPie():
    fig, ax = plt.subplots(figsize=(8, 4), subplot_kw=dict(aspect="equal"))
    recipe  = ['2015y 1249',
               '2016y 1462',
               '2017y 1728',
               '2018y 1771',
               '2019y 978']
    data = [1249,1462,1728,1771,978]

    """
    参数wedgeprops以字典形式传递，设置饼图边界的相关属性，例如圆环宽度0.5
    饼状图默认从x轴正向沿逆时针绘图，参数startangle可指定新的角（例如负40度）度起画
    """
    wedges, texts = ax.pie(data, wedgeprops=dict(width=0.5), startangle=-40)

    # 创建字典bbox_props，设置文本框的边框样式(boxstyle：方框，pad设置方框尺寸)、前景色(fc)为白色(w)、边框颜色(ec)为黑色(k)、线粗(lw)为0.72
    bbox_props = dict(boxstyle="square,pad=0.3", fc="w", ec="k", lw=0.72)

    """
    参数集kw以字典形式传递，包含一系列用于绘图标注的指定参数
    xycoords用于指定点xy的坐标环境，xycoords='data'表示沿用被注释的对象所采用的坐标系（默认设置）
    textcoords用于指定点xytext的坐标环境，textcoords='data'表示沿用被注释的对象所采用的坐标系（默认设置）
    参数arrowprops以字典形式传递，用于控制箭头的诸多属性，如箭头类型(arrowstyle)、箭头连接时的弯曲程度(connectionstyle)
    """
    kw = dict(xycoords='data', textcoords='data', arrowprops=dict(arrowstyle="-"),
              bbox=bbox_props, zorder=0, va="center")

    for i, p in enumerate(wedges):  # 遍历每一个扇形

        ang = (p.theta2 - p.theta1) / 2. + p.theta1  # 锁定扇形夹角的中间位置，对应的度数为ang

        # np.deg2rad(x)将度数x转为弧度(x*pi)/180
        y = np.sin(np.deg2rad(ang))  # np.sin()求正弦
        x = np.cos(np.deg2rad(ang))  # np.cos()求余弦

        """
        np.sign()符号函数：大于0返回1.0，小于0返回-1.0，等于0返回0.0
        参数horizontalalignment用于设置垂直对齐方式，可选参数：left、right、center
        当余弦值x大于0（即标签在饼图右侧时，按框左侧对齐）时，horizontalalignment="left"
        当余弦值x小于0（即标签在饼图左侧时，按框右侧对齐）时，horizontalalignment="right" 
        """
        horizontalalignment = {-1: "right", 1: "left"}[int(np.sign(x))]

        connectionstyle = "angle,angleA=0,angleB={}".format(ang)  # 参数connectionstyle用于控制箭头连接时的弯曲程度
        kw["arrowprops"].update({"connectionstyle": connectionstyle})  # 将connectionstyle更新至参数集kw的参数arrowprops中

        """
        用一个箭头/横线指向要注释的地方，再写上一段话的行为，叫做annotate
        ax.annotate()用于对已绘制的图形做标注
        recipe[i]是第i个注释文本
        size设置字体大小
        xy=(x1, y1)表示在给定的xycoords中，被注释对象的坐标点
        xytext=(x2, y2)表示在给定的textcoords中，注释文本的坐标点
        """
        ax.annotate(recipe[i], size=15, xy=(x, y), xytext=(1.35 * np.sign(x), 1.4 * y),
                    horizontalalignment=horizontalalignment, **kw)

    ax.set_title("")
    plt.show()

axPie()