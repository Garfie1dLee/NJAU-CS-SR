
namespace WinStudent
{
    partial class Frmcustomer
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
            this.CustomerTle = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.BCumtomerName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Bsex = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.BconsumAmount = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.button1 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.CustomerTle,
            this.BCumtomerName,
            this.Bsex,
            this.BconsumAmount});
            this.dataGridView1.Location = new System.Drawing.Point(0, 101);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 82;
            this.dataGridView1.RowTemplate.Height = 37;
            this.dataGridView1.Size = new System.Drawing.Size(1698, 760);
            this.dataGridView1.TabIndex = 0;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // CustomerTle
            // 
            this.CustomerTle.DataPropertyName = "customerTle";
            this.CustomerTle.HeaderText = "顾客手机号";
            this.CustomerTle.MinimumWidth = 10;
            this.CustomerTle.Name = "CustomerTle";
            this.CustomerTle.Width = 200;
            // 
            // BCumtomerName
            // 
            this.BCumtomerName.DataPropertyName = "customerName";
            this.BCumtomerName.HeaderText = "顾客姓名";
            this.BCumtomerName.MinimumWidth = 10;
            this.BCumtomerName.Name = "BCumtomerName";
            this.BCumtomerName.Width = 200;
            // 
            // Bsex
            // 
            this.Bsex.DataPropertyName = "sex";
            this.Bsex.HeaderText = "性别";
            this.Bsex.MinimumWidth = 10;
            this.Bsex.Name = "Bsex";
            this.Bsex.Width = 200;
            // 
            // BconsumAmount
            // 
            this.BconsumAmount.DataPropertyName = "consumAmount";
            this.BconsumAmount.HeaderText = "消费金额";
            this.BconsumAmount.MinimumWidth = 10;
            this.BconsumAmount.Name = "BconsumAmount";
            this.BconsumAmount.Width = 200;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(0, -5);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(1682, 100);
            this.button1.TabIndex = 1;
            this.button1.Text = "更新";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Frmcustomer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1694, 847);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridView1);
            this.Name = "Frmcustomer";
            this.Text = "顾客列表";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.DataGridViewTextBoxColumn CustomerTle;
        private System.Windows.Forms.DataGridViewTextBoxColumn BCumtomerName;
        private System.Windows.Forms.DataGridViewTextBoxColumn Bsex;
        private System.Windows.Forms.DataGridViewTextBoxColumn BconsumAmount;
    }
}