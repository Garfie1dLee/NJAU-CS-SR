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
    public partial class FrmAddEmp : Form
    {
        public FrmAddEmp()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            //1）获取页面信息输入
            int empnum = int.Parse(txtempnum.Text);
            string empname = stringempname.Text.Trim();
            string empsex = rbtMale.Checked ? rbtMale.Text.Trim() : rbtFemale.Text.Trim();
            int empage = int.Parse(txtempage.Text);
            int empsalary = int.Parse(txtempsalary.Text);
            string empposition = stringempposition.Text.Trim();
            //2）判空处理 姓名不可以为空  电话不可以为空
            if (string.IsNullOrEmpty(empname))
            {
                MessageBox.Show("姓名不能为空!", "添加员工提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (string.IsNullOrEmpty(Convert.ToString(empnum)))
            {
                MessageBox.Show("员工编号不能为空!", "添加员工提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            //3）判断 姓名+编号  是否在数据库里已存在 姓名+编号
            string sql = "select count(1) from empinfo where empname=@empname and empnum=@empnum";
            SqlParameter[] paras =
            {
                new SqlParameter("@empname",empname),
                new SqlParameter("@empnum",empnum)
            };
            object o = SqlHelper.ExecuteScalar(sql, paras);
            if (o != null && o != DBNull.Value && ((int)o) > 0)
            {
                MessageBox.Show("该员工已存在!", "添加员工提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            //4）添加入库 sql  参数 执行  完成返回受影响行数
            string sqlAdd = "insert into empinfo(empnum,empname,empsex,empage,empsalary,empposition) values(@empnum,@empname,@empsex,@empage,@empsalary,@empposition)";
            SqlParameter[] parasAdd =
           {
                new SqlParameter("@empnum",empnum),
                new SqlParameter("@empname",empname),
                new SqlParameter("@empsex",empsex),
                new SqlParameter("@empage",empage),
                new SqlParameter("@empsalary",empsalary),
                new SqlParameter("@empposition",empposition)
            };
           int  count = SqlHelper.ExecuteNonQuery(sqlAdd, parasAdd);
            if(count>0)
            {
                MessageBox.Show($"员工:{empname} 添加成功!", "添加学生提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else
            {
                MessageBox.Show("该员工添加失败,请检查!", "添加学生提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
        }

        /// <summary>
        /// 加载班级列表\性别默认选择男
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void FrmAddStudent_Load(object sender, EventArgs e)
        {
            
            rbtMale.Checked = true;
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
