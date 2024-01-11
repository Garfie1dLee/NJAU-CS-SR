namespace WinStudent
{
    partial class FrmMain
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
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.miStudent = new System.Windows.Forms.ToolStripMenuItem();
            this.subAddStudent = new System.Windows.Forms.ToolStripMenuItem();
            this.subStudentList = new System.Windows.Forms.ToolStripMenuItem();
            this.修改学生ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.miClass = new System.Windows.Forms.ToolStripMenuItem();
            this.subAddClass = new System.Windows.Forms.ToolStripMenuItem();
            this.subClassList = new System.Windows.Forms.ToolStripMenuItem();
            this.miGrade = new System.Windows.Forms.ToolStripMenuItem();
            this.subGradeList = new System.Windows.Forms.ToolStripMenuItem();
            this.更新餐桌状态ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.新增顾客ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.顾客ToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.mi1 = new System.Windows.Forms.ToolStripMenuItem();
            this.miExit = new System.Windows.Forms.ToolStripMenuItem();
            this.button1 = new System.Windows.Forms.Button();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.GripMargin = new System.Windows.Forms.Padding(2, 2, 0, 2);
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(32, 32);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.miStudent,
            this.miClass,
            this.miGrade,
            this.toolStripMenuItem1,
            this.mi1,
            this.miExit});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(2474, 39);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // miStudent
            // 
            this.miStudent.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.subAddStudent,
            this.subStudentList,
            this.修改学生ToolStripMenuItem});
            this.miStudent.Name = "miStudent";
            this.miStudent.Size = new System.Drawing.Size(130, 35);
            this.miStudent.Text = "员工管理";
            this.miStudent.Click += new System.EventHandler(this.miStudent_Click);
            // 
            // subAddStudent
            // 
            this.subAddStudent.Name = "subAddStudent";
            this.subAddStudent.Size = new System.Drawing.Size(243, 44);
            this.subAddStudent.Text = "新增员工";
            this.subAddStudent.Click += new System.EventHandler(this.subAddStudent_Click);
            // 
            // subStudentList
            // 
            this.subStudentList.Name = "subStudentList";
            this.subStudentList.Size = new System.Drawing.Size(243, 44);
            this.subStudentList.Text = "员工列表";
            this.subStudentList.Click += new System.EventHandler(this.subStudentList_Click);
            // 
            // 修改学生ToolStripMenuItem
            // 
            this.修改学生ToolStripMenuItem.Name = "修改学生ToolStripMenuItem";
            this.修改学生ToolStripMenuItem.Size = new System.Drawing.Size(243, 44);
            this.修改学生ToolStripMenuItem.Text = "修改员工";
            this.修改学生ToolStripMenuItem.Click += new System.EventHandler(this.修改学生ToolStripMenuItem_Click);
            // 
            // miClass
            // 
            this.miClass.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.subAddClass,
            this.subClassList});
            this.miClass.Name = "miClass";
            this.miClass.Size = new System.Drawing.Size(130, 35);
            this.miClass.Text = "菜谱管理";
            // 
            // subAddClass
            // 
            this.subAddClass.Name = "subAddClass";
            this.subAddClass.Size = new System.Drawing.Size(243, 44);
            this.subAddClass.Text = "新增菜谱";
            this.subAddClass.Click += new System.EventHandler(this.subAddClass_Click);
            // 
            // subClassList
            // 
            this.subClassList.Name = "subClassList";
            this.subClassList.Size = new System.Drawing.Size(243, 44);
            this.subClassList.Text = "菜谱列表";
            this.subClassList.Click += new System.EventHandler(this.subClassList_Click);
            // 
            // miGrade
            // 
            this.miGrade.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.subGradeList,
            this.更新餐桌状态ToolStripMenuItem});
            this.miGrade.Name = "miGrade";
            this.miGrade.Size = new System.Drawing.Size(130, 35);
            this.miGrade.Text = "餐桌管理";
            // 
            // subGradeList
            // 
            this.subGradeList.Name = "subGradeList";
            this.subGradeList.Size = new System.Drawing.Size(291, 44);
            this.subGradeList.Text = "餐桌列表";
            this.subGradeList.Click += new System.EventHandler(this.subGradeList_Click);
            // 
            // 更新餐桌状态ToolStripMenuItem
            // 
            this.更新餐桌状态ToolStripMenuItem.Name = "更新餐桌状态ToolStripMenuItem";
            this.更新餐桌状态ToolStripMenuItem.Size = new System.Drawing.Size(291, 44);
            this.更新餐桌状态ToolStripMenuItem.Text = "更新餐桌状态";
            this.更新餐桌状态ToolStripMenuItem.Click += new System.EventHandler(this.更新餐桌状态ToolStripMenuItem_Click);
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.新增顾客ToolStripMenuItem,
            this.顾客ToolStripMenuItem});
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(130, 35);
            this.toolStripMenuItem1.Text = "顾客管理";
            // 
            // 新增顾客ToolStripMenuItem
            // 
            this.新增顾客ToolStripMenuItem.Name = "新增顾客ToolStripMenuItem";
            this.新增顾客ToolStripMenuItem.Size = new System.Drawing.Size(243, 44);
            this.新增顾客ToolStripMenuItem.Text = "增改顾客";
            this.新增顾客ToolStripMenuItem.Click += new System.EventHandler(this.新增顾客ToolStripMenuItem_Click);
            // 
            // 顾客ToolStripMenuItem
            // 
            this.顾客ToolStripMenuItem.Name = "顾客ToolStripMenuItem";
            this.顾客ToolStripMenuItem.Size = new System.Drawing.Size(243, 44);
            this.顾客ToolStripMenuItem.Text = "顾客列表";
            this.顾客ToolStripMenuItem.Click += new System.EventHandler(this.顾客ToolStripMenuItem_Click);
            // 
            // mi1
            // 
            this.mi1.Name = "mi1";
            this.mi1.Size = new System.Drawing.Size(130, 35);
            this.mi1.Text = "账单查询";
            this.mi1.Click += new System.EventHandler(this.mi1_Click);
            // 
            // miExit
            // 
            this.miExit.Name = "miExit";
            this.miExit.Size = new System.Drawing.Size(130, 35);
            this.miExit.Text = "退出系统";
            this.miExit.Click += new System.EventHandler(this.miExit_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(8, 50);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(152, 72);
            this.button1.TabIndex = 2;
            this.button1.Text = "点菜";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // FrmMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(2474, 1759);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.menuStrip1);
            this.IsMdiContainer = true;
            this.MainMenuStrip = this.menuStrip1;
            this.Margin = new System.Windows.Forms.Padding(6);
            this.MaximizeBox = false;
            this.Name = "FrmMain";
            this.Text = "饭馆点菜系统主页面";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.FrmMain_FormClosing);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem miStudent;
        private System.Windows.Forms.ToolStripMenuItem subAddStudent;
        private System.Windows.Forms.ToolStripMenuItem subStudentList;
        private System.Windows.Forms.ToolStripMenuItem miClass;
        private System.Windows.Forms.ToolStripMenuItem subAddClass;
        private System.Windows.Forms.ToolStripMenuItem subClassList;
        private System.Windows.Forms.ToolStripMenuItem miGrade;
        private System.Windows.Forms.ToolStripMenuItem subGradeList;
        private System.Windows.Forms.ToolStripMenuItem miExit;
        private System.Windows.Forms.ToolStripMenuItem 更新餐桌状态ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem 新增顾客ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem 顾客ToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem mi1;
        private System.Windows.Forms.ToolStripMenuItem 修改学生ToolStripMenuItem;
        private System.Windows.Forms.Button button1;
    }
}