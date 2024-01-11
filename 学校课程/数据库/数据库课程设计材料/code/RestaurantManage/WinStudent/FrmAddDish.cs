using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WinStudent
{
    public partial class FrmAddDish : Form
    {
        public FrmAddDish()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 初始化年级列表
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void FrmAddClass_Load(object sender, EventArgs e)
        {
            InitGradeList();//加载年级列表
        }

        //一个班级,必须属于某个年级  
        private void InitGradeList()
        {
            /*string sql = "select dishnum,dishname from dishinfo";
            DataTable dtGradeList = SqlHelper.GetDataTable(sql);

            cboGrades.DataSource = dtGradeList;
            //年级名称 ---- 项
            cboGrades.DisplayMember = "dishname";//显示的内容
            cboGrades.ValueMember = "dishnum";//值

            cboGrades.SelectedIndex = 0;*/
        }

        /// <summary>
        /// 添加班级
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnAdd_Click(object sender, EventArgs e)
        {
            //信息获取
            string dishName = txtdishName.Text.Trim();
            string dishNum = txtdishNum.Text.Trim();
            string dishPrice = txtdishPrice.Text.Trim();
           // int gradeId = (int)cboGrades.SelectedValue;
           // string remark = txtRemark.Text.Trim();
            //判断是否为空
            if (string.IsNullOrEmpty(dishName))
            {
                MessageBox.Show("菜单名称不能为空!", "添加菜单提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            //判断是否已存在  数据库里去检查--- 与数据库进行交互
            {
                string sqlExists = "select count(1) from manu where dishnum=@dishnum or dishname=@dishname";
                SqlParameter[] paras =
                {
                    new SqlParameter("@dishname",dishName),
                    new SqlParameter("@dishnum",dishNum),
                     new SqlParameter("@dishprice",dishPrice)
                };
                object oCount = SqlHelper.ExecuteScalar(sqlExists, paras);
                if (oCount == null || oCount == DBNull.Value || ((int)oCount) == 0)
                {
                    //添加操作
                    string sqlAdd = "insert into manu (dishname,dishnum,dishprice) values (@dishname,@dishnum,@dishprice)";
                    SqlParameter[] parasAdd =
                    {
                        new SqlParameter("@dishname",dishName),
                        new SqlParameter("@dishnum",dishNum),
                        new SqlParameter("@dishprice",dishPrice)
                    };
                    //执行并返回值
                    int count = SqlHelper.ExecuteNonQuery(sqlAdd, parasAdd);
                    if(count>0)
                    {
                        MessageBox.Show($"菜单名称:{dishName} 添加成功!", "添加菜单提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        
                    }
                    else
                    {
                        MessageBox.Show("菜单名称添加失败!", "添加菜单提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return;
                    }
                }
                else
                {
                    MessageBox.Show("菜单名称或菜单编号已存在!", "添加菜单提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
