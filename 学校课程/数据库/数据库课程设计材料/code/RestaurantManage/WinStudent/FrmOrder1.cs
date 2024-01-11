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
    
    public partial class FrmOrder1 : Form
    {
        string billnum = "S"+DateTime.Now.ToString();
        string paytime = DateTime.Now.ToString();
        static int sum;
        static int actualpay;
        //弹出点菜界面


        FrmDishList frmclasslist = new FrmDishList();

        

        public FrmOrder1()
        {
            InitializeComponent();
            

        }

        private void FrmOrder1_Load(object sender, EventArgs e)
        {
            frmclasslist.Show();


        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            string dishname = txtName.Text.Trim();
            int  dishnum =  int.Parse(txtNum.Text.Trim());
         //   string amount = txtAmount.Text.Trim();
            int dishamount = int.Parse(txtAmount.Text);
            //  string sql1 = "select dishnum,dishname,dishprice from manu";
            int dishprice = 0;
            string sqlprice = "select dishprice from manu where dishnum=@dishnum";
                SqlParameter[] paras123 =
            {
                new SqlParameter("@dishnum",dishnum)
            };
            object o = SqlHelper.ExecuteScalar(sqlprice, paras123);
            dishprice = int.Parse(o.ToString()) ;
            int sumprice = dishprice * dishamount;
            // sql1 += " where 1=1"; 
            //  if (!string.IsNullOrEmpty(name))
            //  {
            //  sql1 += "and  dishname Like @dishname";
            // }
            //  if (!string.IsNullOrEmpty(num))                                         
            //  {
            //    sql1 += " and dishnum =@dishnum";
            //   }



            SqlParameter[] paras =
            {
                new SqlParameter("@billnum",billnum),
                new SqlParameter("@dishname","%"+dishname+"%"),
                new SqlParameter("@dishnum",dishnum),
                new SqlParameter("@dishprice",dishprice),
                new SqlParameter("@dishamount",dishamount),
                new SqlParameter("@sumprice",sumprice)

            };

          //  DataTable dtorder = SqlHelper.GetDataTable(sql1, paras);     //先选出菜品
         //   int count = SqlHelper.ExecuteNonQuery(sql1, paras);          //名字和编号


            string sqlAdd = "insert into dishinfo (billnum,dishname,dishnum,dishamount,dishprice,sumprice) values (@billnum,@dishname,@dishnum,@dishamount,@dishprice,@sumprice)";


           // string sql1 = "insert into dishinfo(dishprice) values (@dishprice) ";
          //  int count1 = SqlHelper.ExecuteNonQuery(sql1);

            int countadd = SqlHelper.ExecuteNonQuery(sqlAdd, paras);

            if (countadd > 0)
            {
                MessageBox.Show($"菜品添加成功!", "添加菜单提示", MessageBoxButtons.OK, MessageBoxIcon.Information);

            }
            else
            {
                MessageBox.Show("菜单添加失败!", "添加菜单提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }






           // SqlParameter[] parasAdd =
{
              //  new SqlParameter("@dishnum",num),
              //  new SqlParameter("@dishname",name),
              //  new SqlParameter("@dishamount",amount)
   
           };


           // int count1 = SqlHelper.ExecuteNonQuery(sqladd, parasAdd);
         //   if (count1 > 0)
          //  {
             //   MessageBox.Show($"顾客:{name} 添加成功!", "添加菜品提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
           // }

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            string sql = "select sum(sumprice) from dishinfo where billnum=@billnum";
            SqlParameter[] paras =
{
                new SqlParameter("@billnum", billnum)

            };
            object o = SqlHelper.ExecuteScalar(sql, paras);
            sum = int.Parse(o.ToString());
            MessageBox.Show($"总金额为:{sum} !", "金额提示", MessageBoxButtons.OK, MessageBoxIcon.Information);

        }

        private void button3_Click(object sender, EventArgs e)
        {
            float discount = 1;
            if (sum > 100)
                discount = 0.9F;
            else if (sum > 300)
                discount = 0.8F;
            else if (sum > 1000)
                discount = 0.6F;
            actualpay = (int)(discount * sum);
            string customerTle = txtTel.Text.Trim();
            int tablenum = int.Parse(txttablenum.Text);
            int empnum = int.Parse(textyuangongnum.Text);
            string sqlAdd = "insert into billinfo(billnum,sumprice,actualpay,paytime,discount,customerTle,tablenum,empnum) values(@billnum,@sumprice,@actualpay,@paytime,@discount,@customerTle,@tablenum,@empnum)";
            SqlParameter[] parasAdd =
           {
                new SqlParameter("@billnum",billnum),
                new SqlParameter("@sumprice",sum),
                new SqlParameter("@paytime",paytime),
                new SqlParameter("@discount",discount),
                new SqlParameter("@actualpay",actualpay),
                new SqlParameter("@customerTle",customerTle),
                new SqlParameter("@tablenum",tablenum),
                new SqlParameter("@empnum",empnum)
            };
            int count = SqlHelper.ExecuteNonQuery(sqlAdd, parasAdd);
            if (count > 0)
            {
                MessageBox.Show($"下单:{billnum} 成功!", "添加下单提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else
            {
                MessageBox.Show("下单失败,请检查!", "添加下单提示", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            String hasoccupy = "no";
            String sql = "update tableinfo set hasoccupy=@hasoccupy where tablenum=@tablenum";
            SqlParameter[] parasAdd11 =
        {
                new SqlParameter("@hasoccupy",hasoccupy),
                new SqlParameter("@tablenum",tablenum)
            };

            int count123 = SqlHelper.ExecuteNonQuery(sql, parasAdd11);
            string sqlAdd1 = "update customer set consumAmount=consumAmount+@actualpay where customerTle=@customerTle";
            SqlParameter[] parasAdd1 =
           {
                new SqlParameter("@customerTle",customerTle),
                new SqlParameter("@actualpay",actualpay)
            };
            int count222 = SqlHelper.ExecuteNonQuery(sqlAdd1, parasAdd1);
            if (count222 > 0)
            {
                MessageBox.Show($"顾客:{customerTle} 已增加此次消费金额!", "修改顾客提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            this.Hide();
            frmclasslist.Hide();







        }
    }
}
