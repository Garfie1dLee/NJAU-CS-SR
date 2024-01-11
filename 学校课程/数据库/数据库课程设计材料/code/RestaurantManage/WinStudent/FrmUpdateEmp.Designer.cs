
namespace WinStudent
{
    partial class FrmUpdateEmp
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
            this.label1 = new System.Windows.Forms.Label();
            this.txtempnum = new System.Windows.Forms.TextBox();
            this.txtempname = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.txtempsex = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.txtempage = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtempsalary = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtempposition = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 143);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(178, 24);
            this.label1.TabIndex = 0;
            this.label1.Text = "修改者员工编号";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // txtempnum
            // 
            this.txtempnum.Location = new System.Drawing.Point(204, 132);
            this.txtempnum.Name = "txtempnum";
            this.txtempnum.Size = new System.Drawing.Size(193, 35);
            this.txtempnum.TabIndex = 1;
            // 
            // txtempname
            // 
            this.txtempname.Location = new System.Drawing.Point(204, 207);
            this.txtempname.Name = "txtempname";
            this.txtempname.Size = new System.Drawing.Size(193, 35);
            this.txtempname.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(50, 218);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(106, 24);
            this.label2.TabIndex = 2;
            this.label2.Text = "员工姓名";
            // 
            // txtempsex
            // 
            this.txtempsex.Location = new System.Drawing.Point(204, 287);
            this.txtempsex.Name = "txtempsex";
            this.txtempsex.Size = new System.Drawing.Size(193, 35);
            this.txtempsex.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(50, 298);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(58, 24);
            this.label3.TabIndex = 4;
            this.label3.Text = "性别";
            // 
            // txtempage
            // 
            this.txtempage.Location = new System.Drawing.Point(204, 358);
            this.txtempage.Name = "txtempage";
            this.txtempage.Size = new System.Drawing.Size(193, 35);
            this.txtempage.TabIndex = 7;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(50, 369);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(58, 24);
            this.label4.TabIndex = 6;
            this.label4.Text = "年龄";
            // 
            // txtempsalary
            // 
            this.txtempsalary.Location = new System.Drawing.Point(204, 430);
            this.txtempsalary.Name = "txtempsalary";
            this.txtempsalary.Size = new System.Drawing.Size(193, 35);
            this.txtempsalary.TabIndex = 9;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(50, 441);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(58, 24);
            this.label5.TabIndex = 8;
            this.label5.Text = "工资";
            // 
            // txtempposition
            // 
            this.txtempposition.Location = new System.Drawing.Point(204, 497);
            this.txtempposition.Name = "txtempposition";
            this.txtempposition.Size = new System.Drawing.Size(193, 35);
            this.txtempposition.TabIndex = 11;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(50, 508);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(58, 24);
            this.label6.TabIndex = 10;
            this.label6.Text = "职位";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(675, 194);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(454, 314);
            this.button1.TabIndex = 12;
            this.button1.Text = "更新";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // FrmUpdateEmp
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1202, 854);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.txtempposition);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.txtempsalary);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.txtempage);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txtempsex);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.txtempname);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.txtempnum);
            this.Controls.Add(this.label1);
            this.Name = "FrmUpdateEmp";
            this.Text = "修改员工";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txtempnum;
        private System.Windows.Forms.TextBox txtempname;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtempsex;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtempage;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtempsalary;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtempposition;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button button1;
    }
}