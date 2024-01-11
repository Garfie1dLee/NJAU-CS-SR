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
    public partial class FrmTableList : Form
    {
        public FrmTableList()
        {
            InitializeComponent();
        }

        private void FrmGradeList_Load(object sender, EventArgs e)
        {
            string sql = "select tablenum,seatamount,hasoccupy from tableinfo";
            DataTable dtGradeList = SqlHelper.GetDataTable(sql);

            dgvGradeList.DataSource = dtGradeList;
        }

        private void dgvGradeList_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
