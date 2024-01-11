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
    public partial class FrmUpdateEmp : Form
    {
        public FrmUpdateEmp()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {

            int empnum = int.Parse(txtempnum.Text);
            string empname = txtempname.Text.Trim();
            string empsex = txtempsex.Text;
            int empage = int.Parse(txtempage.Text);
            int empsalary = int.Parse(txtempsalary.Text);
            string empposition = txtempposition.Text.Trim();

            String sql = "update empinfo set empname=@empname,empsex=@empsex,empage=@empage,empsalary=@empsalary,empposition=@empposition  where empnum=@empnum";

            SqlParameter[] parasAdd =
         {
                new SqlParameter("@empnum",empnum),
                new SqlParameter("@empname",empname),
                new SqlParameter("@empsex",empsex),
                new SqlParameter("@empage",empage),
                new SqlParameter("@empsalary",empsalary),
                new SqlParameter("@empposition",empposition)
            };

            int count = SqlHelper.ExecuteNonQuery(sql, parasAdd);
            if(count>0)
            {
                MessageBox.Show($"员工:{empname} 修改成功!", "添加员工提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }
    }
}
