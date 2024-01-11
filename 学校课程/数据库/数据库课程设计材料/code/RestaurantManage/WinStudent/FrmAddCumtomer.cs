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
    public partial class FrmAddCumtomer : Form
    {
        public FrmAddCumtomer()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string customerTle = txtTle.Text.Trim();
            string customerName = txtName.Text.Trim();
            string sex = txtSex.Text.Trim();
            int consumAmount = int.Parse(txtSum.Text);
            string sqlAdd = "insert into customer(customerTle,customerName,sex,consumAmount) values(@customerTle,@customerName,@sex,@consumAmount)";
            SqlParameter[] parasAdd =
           {
                new SqlParameter("@customerTle",customerTle),
                new SqlParameter("@customerName",customerName),
                new SqlParameter("@sex",sex),
                new SqlParameter("@consumAmount",consumAmount)
            };
            int count = SqlHelper.ExecuteNonQuery(sqlAdd, parasAdd);
            if (count > 0)
            {
                MessageBox.Show($"顾客:{customerName} 添加成功!", "添加顾客提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            string customerTle = txtTle.Text.Trim();
            string customerName = txtName.Text.Trim();
            string sex = txtSex.Text.Trim();
            int consumAmount = int.Parse(txtSum.Text);
            string sqlAdd1 = "update customer set customerName=@customerName,sex=@sex,consumAmount=@consumAmount where customerTle=@customerTle";
            SqlParameter[] parasAdd1 =
           {
                new SqlParameter("@customerTle",customerTle),
                new SqlParameter("@customerName",customerName),
                new SqlParameter("@sex",sex),
                new SqlParameter("@consumAmount",consumAmount)
            };
            int count = SqlHelper.ExecuteNonQuery(sqlAdd1, parasAdd1);
            if (count > 0)
            {
                MessageBox.Show($"顾客:{customerName} 修改成功!", "修改顾客提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }

        }
    }
}
