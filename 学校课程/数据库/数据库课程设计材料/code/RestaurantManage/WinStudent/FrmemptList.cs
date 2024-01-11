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
    public partial class FrmemptList : Form
    {
        public FrmemptList()
        {
            InitializeComponent();
        }

        //单例  只有一个实例 
        private static FrmemptList frmStudentList = null;
        public static FrmemptList CreateInstance()
        {
            if(frmStudentList==null ||frmStudentList.IsDisposed)
                frmStudentList = new FrmemptList();
            return frmStudentList;
        }

        //加载班级列表、加载所有的学生信息
        private void FrmStudentList_Load(object sender, EventArgs e)
        {
            
            LoadAllStudentList();//加载所有学生信息
        }

        private void LoadAllStudentList()
        {
            string sql = "select empnum,empname,empsex,empage,empsalary,empposition from empinfo";
            //加载数据
            DataTable dtemps = SqlHelper.GetDataTable(sql);
            //我只想显示固定的列  
            //dgvStudents.AutoGenerateColumns = false;

            //绑定数据
            empbiao.DataSource = dtemps;
           
        }
        /// <summary>
        /// 查询学生信息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnFind_Click(object sender, EventArgs e)
        {
            //接收条件设置信
            int empnum = int.Parse(txtStuName.Text);
            //查询sql
            string sql = "select empnum,empname,empsex,empage,empsalary,empposition from empinfo";
            sql += " where 1=1 ";
            if(empnum>0)
            {
                sql += " and empnum = @empnum";
            }
            sql += " order by empnum";

            SqlParameter[] paras =
            {
                new SqlParameter("@empnum",empnum)
            };
            //加载数据
            DataTable dtStudents = SqlHelper.GetDataTable(sql,paras);
            //组装


            //我只想显示固定的列  
            //dgvStudents.AutoGenerateColumns = false;
            //绑定数据
            empbiao.DataSource = dtStudents;



    }

      
    }
}
