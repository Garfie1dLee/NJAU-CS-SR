using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WinStudent
{
    public partial class FrmMain : Form
    {
        public FrmMain()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 新增学生
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void subAddStudent_Click(object sender, EventArgs e)
        {
            FrmAddEmp fAddStudent = new FrmAddEmp();
            fAddStudent.MdiParent = this;
            fAddStudent.Show();//顶级窗体  不能显示到MDI容器中

        }

        /// <summary>
        /// 学生列表   不可以同时打开多个页面
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void subStudentList_Click(object sender, EventArgs e)
        {
            bool bl = CheckForm(typeof(FrmemptList).Name);
            if (!bl)
            {
                FrmemptList fStudentList = new FrmemptList();
                fStudentList.MdiParent = this;
                fStudentList.Show();
            }

        }

        /// <summary>
        /// 检查窗体是否已经打开
        /// </summary>
        /// <param name="formName"></param>
        /// <returns></returns>
        private bool CheckForm(string formName)
        {
            bool bl = false;
            foreach (Form f in Application.OpenForms)
            {
                if (f.Name == formName)
                {
                    bl = true;
                    f.Activate();
                    break;
                }
            }
            return bl;
        }

        /// <summary>
        /// 新增班级
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void subAddClass_Click(object sender, EventArgs e)
        {
            FrmAddDish fAddClass = new FrmAddDish();
            fAddClass.MdiParent = this;
            fAddClass.Show();
        }

        /// <summary>
        /// 班级列表
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void subClassList_Click(object sender, EventArgs e)
        {
            bool bl = CheckForm(typeof(FrmDishList).Name);
            if (!bl)
            {
                FrmDishList fClassList = new FrmDishList();
                fClassList.MdiParent = this;
                fClassList.Show();
            }
        }

        /// <summary>
        /// 年级列表
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void subGradeList_Click(object sender, EventArgs e)
        {
            bool bl = CheckForm(typeof(FrmTableList).Name);
            if (!bl)
            {
                FrmTableList fGradeList = new FrmTableList();
                fGradeList.MdiParent = this;
                fGradeList.Show();
            }
        }

        /// <summary>
        ///窗体关闭，退出程序
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void FrmMain_FormClosing(object sender, FormClosingEventArgs e)
        {
            DialogResult result = MessageBox.Show("您确定要退出系统吗？", "退出提示", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if(result==DialogResult.Yes)
            {
                Application.ExitThread();//退出当前线程上的消息循环
            }
            else
            {
                e.Cancel = true;//手动取消
            }

        }
        //退出系统
        private void miExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
           
        }

        private void miStudent_Click(object sender, EventArgs e)
        {

        }

        private void 更新餐桌状态ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmUpdateTable frmUpdateTable = new FrmUpdateTable();
            frmUpdateTable.Show();
        }

        private void 修改学生ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmUpdateEmp frmUpdateEmp = new FrmUpdateEmp();
            frmUpdateEmp.Show();
        }

        private void 顾客ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Frmcustomer frmcustomer = new Frmcustomer();
            frmcustomer.Show();
        }

        private void 新增顾客ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FrmAddCumtomer frmAddCumtomer = new FrmAddCumtomer();
            frmAddCumtomer.Show();
        }

        private void mi1_Click(object sender, EventArgs e)
        {
            Frmbill frmbill = new Frmbill();
            frmbill.MdiParent = this;
            frmbill.Show();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            FrmORDER frmORDER = new FrmORDER();
            frmORDER.Show();

        }

        private void 账单查询ToolStripMenuItem_Click(object sender, EventArgs e)
        {
           
        }
    }
}
