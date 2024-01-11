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
    public partial class FrmDishList : Form
    {
        public FrmDishList()
        {
            InitializeComponent();
        }

       /// <summary>
       /// 初始化年级列表、所有班级列表信息
       /// </summary>
       /// <param name="sender"></param>
       /// <param name="e"></param>
        private void FrmClassList_Load(object sender, EventArgs e)
        {
            InitGrades();//加载年级列表
            InitAllClasses();//加载所有的班级信息
        }

        private void InitAllClasses()
        {
            string sql = "select dishnum,dishname,dishprice from manu";
            DataTable dtClasses = SqlHelper.GetDataTable(sql);

            dgvClassList.DataSource = dtClasses;
        }

        private void InitGrades()
        {
            string sql = "select dishnum,dishname,dishprice from manu";
            DataTable dtGradeList = SqlHelper.GetDataTable(sql);
            //添加 一个  请选择   项  不可以放到数据库  它就是没有意义的数据
            //添加一行
            DataRow dr = dtGradeList.NewRow();
            dr["dishnum"] = 0;
            dr["dishname"] = "请选择";
            //dtGradeList.Rows.Add(dr);//添加到最后一个
            dtGradeList.Rows.InsertAt(dr, 0);


            //cboGrades.DataSource = dtGradeList;
            //年级名称 ---- 项
            //cboGrades.DisplayMember = "dishname";//显示的内容
            //cboGrades.ValueMember = "dishnum";//值

            //cboGrades.SelectedIndex = 0;
        }

        /// <summary>
        /// 查询班级信息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnFind_Click(object sender, EventArgs e)
        {
            //获取查询条件
            // int gradeId = (int)cboGrades.SelectedValue; 
            string  dishName= txtDishName.Text.Trim();
            string className = txtClassName.Text.Trim();
            //
            string sql = "select dishnum,dishname,dishprice from manu";
            sql += " where 1=1";
            if(!string.IsNullOrEmpty(dishName))
            {
                sql += "and  dishName Like @dishname";
            }
            if(!string.IsNullOrEmpty(className))                                            //className是编号
            {
                sql += " and dishnum =@dishnum";
            }

            SqlParameter[] paras =
            {
                new SqlParameter("@dishname","%"+dishName+"%"),
                new SqlParameter("@dishnum",className)
            };

            DataTable dtClasses = SqlHelper.GetDataTable(sql,paras);

            dgvClassList.DataSource = dtClasses;
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }
    }
}
