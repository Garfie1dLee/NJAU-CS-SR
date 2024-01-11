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
    public partial class FrmORDER : Form
    {
        public FrmORDER()
        {
            InitializeComponent();
        }

        private void FrmORDER_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string customerTle = txtTel.Text.Trim();
            string customerName = txtName.Text.Trim();
            string sex = txtSex.Text.Trim();
            string paytime = DateTime.Now.ToString();
            int tablenum = int.Parse(txttablenum.Text);
            int consumAmount = 0;
            
            string sql = "select count(1) from customer where customerTle=@customerTle";
            SqlParameter[] paras =
            {
                new SqlParameter("@customerTle",customerTle),
            };
            object o = SqlHelper.ExecuteScalar(sql, paras);
            if (o != null && o != DBNull.Value && ((int)o) > 0)
            {
                
               
            }
            else
            {
                string sqlAdd = "insert into customer(customerTle,customerName,sex,consumAmount) values(@customerTle,@customerName,@sex,@consumAmount)";
                SqlParameter[] parasAdd =
               {
                new SqlParameter("@customerTle",customerTle),
                new SqlParameter("@customerName",customerName),
                new SqlParameter("@sex",sex),
                new SqlParameter("@consumAmount",consumAmount)

            };
                int count1 = SqlHelper.ExecuteNonQuery(sqlAdd, parasAdd);
                if (count1 > 0)
                {
                    MessageBox.Show($"顾客:{customerName} 添加成功!", "添加顾客提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }

            String hasoccupy = "yes";
            String sqltable = "update tableinfo set hasoccupy=@hasoccupy where tablenum=@tablenum";

            string sqltest = "select hasoccupy from tableinfo where tablenum=@tablenum";
            SqlParameter[] parastest =
            {
                new SqlParameter("@tablenum",tablenum),
            };
            object o1 = SqlHelper.ExecuteScalar(sqltest, parastest);
            if (o1.ToString() == "yes")
            {
                MessageBox.Show("该餐桌已占用", "占用餐桌提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            else
            {
                SqlParameter[] parastable =
            {
                new SqlParameter("@hasoccupy",hasoccupy),
                new SqlParameter("@tablenum",tablenum)
            };

                int count = SqlHelper.ExecuteNonQuery(sqltable, parastable);
                if (count > 0)
                {
                    MessageBox.Show($"餐桌:{tablenum} 预订成功!", "添加餐桌提示", MessageBoxButtons.OK, MessageBoxIcon.Information);

                   
                    FrmOrder1 frmOder1 = new FrmOrder1();

                    frmOder1.Show();
                    this.Hide();
                    
                }
            }

            //

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
