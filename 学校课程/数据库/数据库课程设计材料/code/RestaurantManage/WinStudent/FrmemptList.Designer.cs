namespace WinStudent
{
    partial class FrmemptList
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
            this.label2 = new System.Windows.Forms.Label();
            this.txtStuName = new System.Windows.Forms.TextBox();
            this.empbiao = new System.Windows.Forms.DataGridView();
            this.empnum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.empname = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.empsex = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.empage = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.empsalary = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.empposition = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.empbiao)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBox1.Controls.Add(this.btnFind);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.txtStuName);
            this.groupBox1.Location = new System.Drawing.Point(24, 24);
            this.groupBox1.Margin = new System.Windows.Forms.Padding(6);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Padding = new System.Windows.Forms.Padding(6);
            this.groupBox1.Size = new System.Drawing.Size(1258, 176);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "查询条件：";
            // 
            // btnFind
            // 
            this.btnFind.Location = new System.Drawing.Point(621, 61);
            this.btnFind.Margin = new System.Windows.Forms.Padding(6);
            this.btnFind.Name = "btnFind";
            this.btnFind.Size = new System.Drawing.Size(150, 46);
            this.btnFind.TabIndex = 4;
            this.btnFind.Text = "查询";
            this.btnFind.UseVisualStyleBackColor = true;
            this.btnFind.Click += new System.EventHandler(this.btnFind_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(241, 75);
            this.label2.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(130, 24);
            this.label2.TabIndex = 3;
            this.label2.Text = "员工编号：";
            // 
            // txtStuName
            // 
            this.txtStuName.Location = new System.Drawing.Point(366, 64);
            this.txtStuName.Margin = new System.Windows.Forms.Padding(6);
            this.txtStuName.Name = "txtStuName";
            this.txtStuName.Size = new System.Drawing.Size(196, 35);
            this.txtStuName.TabIndex = 1;
            // 
            // empbiao
            // 
            this.empbiao.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.empbiao.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.empbiao.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.empnum,
            this.empname,
            this.empsex,
            this.empage,
            this.empsalary,
            this.empposition});
            this.empbiao.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.empbiao.Location = new System.Drawing.Point(0, 234);
            this.empbiao.Margin = new System.Windows.Forms.Padding(6);
            this.empbiao.Name = "empbiao";
            this.empbiao.RowHeadersWidth = 82;
            this.empbiao.RowTemplate.Height = 23;
            this.empbiao.Size = new System.Drawing.Size(1306, 608);
            this.empbiao.TabIndex = 1;
            // 
            // empnum
            // 
            this.empnum.DataPropertyName = "empnum";
            this.empnum.HeaderText = "员工编号";
            this.empnum.MinimumWidth = 10;
            this.empnum.Name = "empnum";
            this.empnum.ReadOnly = true;
            // 
            // empname
            // 
            this.empname.DataPropertyName = "empname";
            this.empname.HeaderText = "员工姓名";
            this.empname.MinimumWidth = 10;
            this.empname.Name = "empname";
            this.empname.ReadOnly = true;
            // 
            // empsex
            // 
            this.empsex.DataPropertyName = "empsex";
            this.empsex.HeaderText = "性别";
            this.empsex.MinimumWidth = 10;
            this.empsex.Name = "empsex";
            this.empsex.ReadOnly = true;
            // 
            // empage
            // 
            this.empage.DataPropertyName = "empage";
            this.empage.HeaderText = "年龄";
            this.empage.MinimumWidth = 10;
            this.empage.Name = "empage";
            this.empage.ReadOnly = true;
            // 
            // empsalary
            // 
            this.empsalary.DataPropertyName = "empsalary";
            this.empsalary.HeaderText = "工资";
            this.empsalary.MinimumWidth = 10;
            this.empsalary.Name = "empsalary";
            this.empsalary.ReadOnly = true;
            // 
            // empposition
            // 
            this.empposition.DataPropertyName = "empposition";
            this.empposition.HeaderText = "职位";
            this.empposition.MinimumWidth = 10;
            this.empposition.Name = "empposition";
            // 
            // FrmStudentList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1306, 842);
            this.Controls.Add(this.empbiao);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(6);
            this.Name = "FrmStudentList";
            this.Text = "员工列表";
            this.Load += new System.EventHandler(this.FrmStudentList_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.empbiao)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnFind;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtStuName;
        private System.Windows.Forms.DataGridView empbiao;
        private System.Windows.Forms.DataGridViewTextBoxColumn empnum;
        private System.Windows.Forms.DataGridViewTextBoxColumn empname;
        private System.Windows.Forms.DataGridViewTextBoxColumn empsex;
        private System.Windows.Forms.DataGridViewTextBoxColumn empage;
        private System.Windows.Forms.DataGridViewTextBoxColumn empsalary;
        private System.Windows.Forms.DataGridViewTextBoxColumn empposition;
    }
}