using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace WinStudent
{
    public partial class FrmLogin : Form
    {
        public FrmLogin()
        {
            InitializeComponent();
        }
        /// <summary>
        /// 登录系统
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnLogin_Click(object sender, EventArgs e)
        {
            //获取用户输入信息
            string uName = txtUserName.Text.Trim();
            string uPwd = txtUserPwd.Text.Trim();
            //判断是否为空
            if(string.IsNullOrEmpty(uName))
            {
                MessageBox.Show("请输入账号!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtUserName.Focus();
                return;
            }
            if (string.IsNullOrEmpty(uPwd))
            {
                MessageBox.Show("请输入密码!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtUserPwd.Focus();
                return;
            }
            //与数据库通信  检查输入与数据库中是否一致
            {

                //写查询语句 拼接式 Sql注入  推荐大家使用参数化Sql
                string sql = "select count(1) from UserInfos where UserName=@UserName and UserPwd=@UserPwd";
         
                SqlParameter[] paras =
                {
                    new SqlParameter("@UserName", uName),
                    new SqlParameter("@UserPwd", uPwd)
                };

                ////建立与数据库的连接
                //// 连接字符串 --- 钥匙  
                ////string connString = "server=.;database=StudentDB;Integrated Security=true";//字符串   Windows身份验证
                //string connString = "server=.;database=StudentDB;uid=lyc;pwd=123456;";//Sql Server 身份验证    Data Source    Initial Catalog    User Id   Password
                //SqlConnection conn = new SqlConnection(connString);
                //添加参数
                //SqlParameter paraUName = new SqlParameter("@UserName", uName);
                //SqlParameter paraUPwd = new SqlParameter("@UserPwd", uPwd);
                ////创建Command对象
                //SqlCommand cmd = new SqlCommand(sql, conn);
                ////cmd.CommandType = CommandType.StoredProcedure;//存储过程
                //cmd.Parameters.Clear();
                ////cmd.Parameters.Add(paraUName);
                ////cmd.Parameters.Add(paraUPwd);
                //cmd.Parameters.AddRange(paras);
                ////打开连接
                //conn.Open(); //最晚打开  最早关闭
                ////执行命令  要求必须在连接状态  Opened
                //object o = cmd.ExecuteScalar();//执行查询,返回结果集第一行第一列的值,忽略其他行或列
                ////关闭连接
                //conn.Close();

                //调用
              
                object o = SqlHelper.ExecuteScalar(sql, paras);

                //处理结果
                if (o==null||o==DBNull.Value ||((int)o)==0)
                {
                    MessageBox.Show("登录账号或密码有错,请检查!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                else
                {
                    MessageBox.Show("登录成功!", "登录提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    //转到主页面
                    FrmMain fMain = new FrmMain();
                    fMain.Show();
                    this.Hide();
                }
            }




          


        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
           // Application.Exit();
        }

        private void txtUserName_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
