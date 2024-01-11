namespace WinStudent
{
    partial class FrmDishList
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnFind = new System.Windows.Forms.Button();
            this.txtClassName = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.dgvClassList = new System.Windows.Forms.DataGridView();
            this.txtDishName = new System.Windows.Forms.TextBox();
            this.dishnum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dishname = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dishprice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvClassList)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBox1.Controls.Add(this.txtDishName);
            this.groupBox1.Controls.Add(this.btnFind);
            this.groupBox1.Controls.Add(this.txtClassName);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(16, 15);
            this.groupBox1.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Padding = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.groupBox1.Size = new System.Drawing.Size(450, 125);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "查询条件:";
            // 
            // btnFind
            // 
            this.btnFind.Location = new System.Drawing.Point(164, 79);
            this.btnFind.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnFind.Name = "btnFind";
            this.btnFind.Size = new System.Drawing.Size(100, 29);
            this.btnFind.TabIndex = 4;
            this.btnFind.Text = "查询";
            this.btnFind.UseVisualStyleBackColor = true;
            this.btnFind.Click += new System.EventHandler(this.btnFind_Click);
            // 
            // txtClassName
            // 
            this.txtClassName.Location = new System.Drawing.Point(347, 26);
            this.txtClassName.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.txtClassName.Name = "txtClassName";
            this.txtClassName.Size = new System.Drawing.Size(84, 25);
            this.txtClassName.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(271, 28);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(75, 15);
            this.label2.TabIndex = 2;
            this.label2.Text = "菜谱编号:";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(25, 28);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(75, 15);
            this.label1.TabIndex = 0;
            this.label1.Text = "菜谱名称:";
            // 
            // dgvClassList
            // 
            this.dgvClassList.AllowUserToAddRows = false;
            this.dgvClassList.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvClassList.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvClassList.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dishnum,
            this.dishname,
            this.dishprice});
            this.dgvClassList.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.dgvClassList.Location = new System.Drawing.Point(0, 164);
            this.dgvClassList.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.dgvClassList.Name = "dgvClassList";
            this.dgvClassList.RowHeadersWidth = 51;
            this.dgvClassList.RowTemplate.Height = 23;
            this.dgvClassList.Size = new System.Drawing.Size(482, 395);
            this.dgvClassList.TabIndex = 1;
            // 
            // txtDishName
            // 
            this.txtDishName.Location = new System.Drawing.Point(107, 25);
            this.txtDishName.Name = "txtDishName";
            this.txtDishName.Size = new System.Drawing.Size(157, 25);
            this.txtDishName.TabIndex = 2;
            // 
            // dishnum
            // 
            this.dishnum.DataPropertyName = "dishnum";
            this.dishnum.HeaderText = "菜谱编号";
            this.dishnum.MinimumWidth = 6;
            this.dishnum.Name = "dishnum";
            this.dishnum.ReadOnly = true;
            // 
            // dishname
            // 
            this.dishname.DataPropertyName = "dishname";
            this.dishname.HeaderText = "菜谱名称";
            this.dishname.MinimumWidth = 6;
            this.dishname.Name = "dishname";
            this.dishname.ReadOnly = true;
            // 
            // dishprice
            // 
            this.dishprice.DataPropertyName = "dishprice";
            this.dishprice.HeaderText = "菜谱价格";
            this.dishprice.MinimumWidth = 6;
            this.dishprice.Name = "dishprice";
            this.dishprice.ReadOnly = true;
            // 
            // FrmClassList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(482, 559);
            this.Controls.Add(this.dgvClassList);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.MaximizeBox = false;
            this.Name = "FrmClassList";
            this.Text = "菜谱列表页面";
            this.Load += new System.EventHandler(this.FrmClassList_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvClassList)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnFind;
        private System.Windows.Forms.TextBox txtClassName;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView dgvClassList;
        private System.Windows.Forms.TextBox txtDishName;
        private System.Windows.Forms.DataGridViewTextBoxColumn dishnum;
        private System.Windows.Forms.DataGridViewTextBoxColumn dishname;
        private System.Windows.Forms.DataGridViewTextBoxColumn dishprice;
    }
}