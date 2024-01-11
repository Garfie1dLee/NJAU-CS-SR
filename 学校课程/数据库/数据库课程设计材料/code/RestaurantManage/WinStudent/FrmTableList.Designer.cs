namespace WinStudent
{
    partial class FrmTableList
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
            this.dgvGradeList = new System.Windows.Forms.DataGridView();
            this.tablenum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.seatamount = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.hasoccupy = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dgvGradeList)).BeginInit();
            this.SuspendLayout();
            // 
            // dgvGradeList
            // 
            this.dgvGradeList.AllowUserToAddRows = false;
            this.dgvGradeList.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvGradeList.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvGradeList.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.tablenum,
            this.seatamount,
            this.hasoccupy});
            this.dgvGradeList.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvGradeList.Location = new System.Drawing.Point(0, 0);
            this.dgvGradeList.Margin = new System.Windows.Forms.Padding(6);
            this.dgvGradeList.Name = "dgvGradeList";
            this.dgvGradeList.RowHeadersWidth = 82;
            this.dgvGradeList.RowTemplate.Height = 23;
            this.dgvGradeList.Size = new System.Drawing.Size(602, 726);
            this.dgvGradeList.TabIndex = 0;
            this.dgvGradeList.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvGradeList_CellContentClick);
            // 
            // tablenum
            // 
            this.tablenum.DataPropertyName = "tablenum";
            this.tablenum.HeaderText = "餐桌编号";
            this.tablenum.MinimumWidth = 10;
            this.tablenum.Name = "tablenum";
            this.tablenum.ReadOnly = true;
            // 
            // seatamount
            // 
            this.seatamount.DataPropertyName = "seatamount";
            this.seatamount.HeaderText = "座位数量";
            this.seatamount.MinimumWidth = 10;
            this.seatamount.Name = "seatamount";
            this.seatamount.ReadOnly = true;
            // 
            // hasoccupy
            // 
            this.hasoccupy.DataPropertyName = "hasoccupy";
            this.hasoccupy.HeaderText = "占用状态";
            this.hasoccupy.MinimumWidth = 10;
            this.hasoccupy.Name = "hasoccupy";
            // 
            // FrmGradeList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(602, 726);
            this.Controls.Add(this.dgvGradeList);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(6);
            this.MaximizeBox = false;
            this.Name = "FrmGradeList";
            this.Text = "餐桌列表页面";
            this.Load += new System.EventHandler(this.FrmGradeList_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgvGradeList)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dgvGradeList;
        private System.Windows.Forms.DataGridViewTextBoxColumn tablenum;
        private System.Windows.Forms.DataGridViewTextBoxColumn seatamount;
        private System.Windows.Forms.DataGridViewTextBoxColumn hasoccupy;
    }
}