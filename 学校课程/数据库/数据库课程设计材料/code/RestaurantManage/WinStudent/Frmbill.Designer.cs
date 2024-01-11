
namespace WinStudent
{
    partial class Frmbill
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.billnum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sumprice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.discount = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.actualpay = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.paytime = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.customerTle = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tablenum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.empnum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.billnum,
            this.sumprice,
            this.discount,
            this.actualpay,
            this.paytime,
            this.customerTle,
            this.tablenum,
            this.empnum});
            this.dataGridView1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.dataGridView1.Location = new System.Drawing.Point(0, 83);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 82;
            this.dataGridView1.RowTemplate.Height = 37;
            this.dataGridView1.Size = new System.Drawing.Size(1598, 683);
            this.dataGridView1.TabIndex = 0;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(-12, -2);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(1610, 82);
            this.button1.TabIndex = 1;
            this.button1.Text = "更新";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // billnum
            // 
            this.billnum.DataPropertyName = "billnum";
            this.billnum.HeaderText = "账单编号";
            this.billnum.MinimumWidth = 10;
            this.billnum.Name = "billnum";
            this.billnum.Width = 140;
            // 
            // sumprice
            // 
            this.sumprice.DataPropertyName = "sumprice";
            this.sumprice.HeaderText = "总金额";
            this.sumprice.MinimumWidth = 10;
            this.sumprice.Name = "sumprice";
            this.sumprice.Width = 140;
            // 
            // discount
            // 
            this.discount.DataPropertyName = "discount";
            this.discount.HeaderText = "折扣";
            this.discount.MinimumWidth = 10;
            this.discount.Name = "discount";
            this.discount.Width = 140;
            // 
            // actualpay
            // 
            this.actualpay.DataPropertyName = "actualpay";
            this.actualpay.HeaderText = "实际付款";
            this.actualpay.MinimumWidth = 10;
            this.actualpay.Name = "actualpay";
            this.actualpay.Width = 140;
            // 
            // paytime
            // 
            this.paytime.DataPropertyName = "paytime";
            this.paytime.HeaderText = "付款时间";
            this.paytime.MinimumWidth = 6;
            this.paytime.Name = "paytime";
            this.paytime.Width = 125;
            // 
            // customerTle
            // 
            this.customerTle.DataPropertyName = "customerTle";
            this.customerTle.HeaderText = "顾客电话";
            this.customerTle.MinimumWidth = 10;
            this.customerTle.Name = "customerTle";
            this.customerTle.Width = 140;
            // 
            // tablenum
            // 
            this.tablenum.DataPropertyName = "tablenum";
            this.tablenum.HeaderText = "餐桌号";
            this.tablenum.MinimumWidth = 10;
            this.tablenum.Name = "tablenum";
            this.tablenum.Width = 140;
            // 
            // empnum
            // 
            this.empnum.DataPropertyName = "empnum";
            this.empnum.HeaderText = "员工编号";
            this.empnum.MinimumWidth = 10;
            this.empnum.Name = "empnum";
            this.empnum.Width = 140;
            // 
            // Frmbill
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1598, 766);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridView1);
            this.MaximizeBox = false;
            this.Name = "Frmbill";
            this.Text = "Frmbill";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.DataGridViewTextBoxColumn billnum;
        private System.Windows.Forms.DataGridViewTextBoxColumn sumprice;
        private System.Windows.Forms.DataGridViewTextBoxColumn discount;
        private System.Windows.Forms.DataGridViewTextBoxColumn actualpay;
        private System.Windows.Forms.DataGridViewTextBoxColumn paytime;
        private System.Windows.Forms.DataGridViewTextBoxColumn customerTle;
        private System.Windows.Forms.DataGridViewTextBoxColumn tablenum;
        private System.Windows.Forms.DataGridViewTextBoxColumn empnum;
        //private readonly object Frmbill;
    }
}