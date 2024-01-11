using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Configuration;
using System.Data;

namespace WinStudent
{
    public class SqlHelper
    {
        //连接字符串
        private static readonly string connString = ConfigurationManager.ConnectionStrings["connStr"].ConnectionString; 
        public static object ExecuteScalar(string sql,params SqlParameter[] paras)
        {
            object o = null;
            using (SqlConnection conn = new SqlConnection(connString))
            {
                //创建Command对象
                SqlCommand cmd = new SqlCommand(sql, conn);
                //cmd.CommandType = CommandType.StoredProcedure;//存储过程
                cmd.Parameters.Clear();
                cmd.Parameters.AddRange(paras);
                //打开连接
                conn.Open(); //最晚打开  最早关闭             
                o = cmd.ExecuteScalar();//执行查询,返回结果集第一行第一列的值,忽略其他行或列
                //关闭连接
                //conn.Close();
            }
            return o;
        }

        /// <summary>
        /// 返回DataTable
        /// </summary>
        /// <param name="sql"></param>
        /// <param name="paras"></param>
        /// <returns></returns>
        public static DataTable GetDataTable(string sql,params SqlParameter[] paras)
        {
            DataTable dt = new DataTable();
            using (SqlConnection conn = new SqlConnection(connString))
            {
                //创建Command对象
                SqlCommand cmd = new SqlCommand(sql, conn);
                //cmd.CommandType = CommandType.StoredProcedure;//存储过程
                if (paras!=null)
                {
                    cmd.Parameters.Clear();
                    cmd.Parameters.AddRange(paras);
                }

                //打开连接
                conn.Open(); //这里打开conn 可以吗?  可以的   后面,da就不会再去关闭   
                //断开式连接  是不是不用连接数据库呢?  不是  
                //执行命令  一定是Command来完成
                SqlDataAdapter da = new SqlDataAdapter();
                da.SelectCommand = cmd;

                //SqlDataAdapter da = new SqlDataAdapter(sql, conn);
                //打开conn  OPened
                //数据填充
                da.Fill(dt);
                //关闭conn 
                //关闭连接
                //conn.Close();
            }
            return dt;
        }

        /// <summary>
        /// 返回受影响的行数  Insert Update Delete
        /// </summary>
        /// <param name="sql"></param>
        /// <param name="paras"></param>
        /// <returns></returns>
        public static int ExecuteNonQuery(string sql,params SqlParameter[] paras)
        {
            int count = 0;
            using (SqlConnection conn = new SqlConnection(connString))
            {
                //创建Command对象
                SqlCommand cmd = new SqlCommand(sql, conn);
                //cmd.CommandType = CommandType.StoredProcedure;//存储过程
                cmd.Parameters.Clear();
                cmd.Parameters.AddRange(paras);   
                //打开连接
                conn.Open(); //最晚打开  最早关闭             
                count = cmd.ExecuteNonQuery();//执行T-SQL语句,返回受影响的行数
                //关闭连接
                //conn.Close();
            }
            return count;
        }


    }
}
