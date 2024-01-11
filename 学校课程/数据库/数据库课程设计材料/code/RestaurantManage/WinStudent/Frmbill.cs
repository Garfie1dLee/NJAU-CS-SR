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
    public partial class Frmbill : Form
    {
        public Frmbill()
        {
            InitializeComponent();
           
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

       // private void Frmbill_Load(object sender, EventArgs e)
       // {
           // string sql = "select * from  billinfo1";
         //   DataTable dtGradeList = SqlHelper.GetDataTable(sql);

         //   dataGridView1.DataSource = dtGradeList;

       // }

        private void button1_Click(object sender, EventArgs e)
        {
            string sql = "select * from  billinfo";
            DataTable dtGradeList = SqlHelper.GetDataTable(sql);

            dataGridView1.DataSource = dtGradeList;


        }
    }
}
