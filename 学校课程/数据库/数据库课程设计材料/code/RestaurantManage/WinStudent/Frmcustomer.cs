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
    public partial class Frmcustomer : Form
    {
        public Frmcustomer()
        {
            InitializeComponent();
        }
    


        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string sql = "select customerTle,customerName,sex,consumAmount from customer";
            DataTable dtG = SqlHelper.GetDataTable(sql);

            dataGridView1.DataSource = dtG;
        }
    }
}
