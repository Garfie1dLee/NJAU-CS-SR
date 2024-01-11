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
    public partial class FrmUpdateTable : Form
    {
        public FrmUpdateTable()
        {
            InitializeComponent();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            int tablenum = int.Parse(textBox1.Text);
            String hasoccupy = textBox2.Text;
            String sql = "update tableinfo set hasoccupy=@hasoccupy where tablenum=@tablenum";
            SqlParameter[] parasAdd =
        {
                new SqlParameter("@hasoccupy",hasoccupy),
                new SqlParameter("@tablenum",tablenum)
            };

            int count = SqlHelper.ExecuteNonQuery(sql, parasAdd);
                if (count > 0)
            {
                MessageBox.Show($"餐桌:{tablenum} 修改成功!", "添加餐桌提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
               
            }

           

        }

       
    }
  
}
