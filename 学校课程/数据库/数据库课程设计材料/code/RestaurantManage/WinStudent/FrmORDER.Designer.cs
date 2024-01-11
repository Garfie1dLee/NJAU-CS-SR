
namespace WinStudent
{
    partial class FrmORDER
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
            this.txtTel = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.txtName = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtSex = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txttablenum = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // txtTel
            // 
            this.txtTel.Location = new System.Drawing.Point(204, 21);
            this.txtTel.Name = "txtTel";
            this.txtTel.Size = new System.Drawing.Size(577, 35);
            this.txtTel.TabIndex = 5;
            this.txtTel.TextChanged += new System.EventHandler(this.textBox3_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(28, 24);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(154, 24);
            this.label3.TabIndex = 4;
            this.label3.Text = "顾客手机号：";
            // 
            // txtName
            // 
            this.txtName.Location = new System.Drawing.Point(204, 78);
            this.txtName.Name = "txtName";
            this.txtName.Size = new System.Drawing.Size(577, 35);
            this.txtName.TabIndex = 7;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(28, 82);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(130, 24);
            this.label4.TabIndex = 6;
            this.label4.Text = "顾客姓名：";
            // 
            // txtSex
            // 
            this.txtSex.Location = new System.Drawing.Point(204, 136);
            this.txtSex.Name = "txtSex";
            this.txtSex.Size = new System.Drawing.Size(577, 35);
            this.txtSex.TabIndex = 9;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(28, 139);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(130, 24);
            this.label5.TabIndex = 8;
            this.label5.Text = "顾客性别：";
            // 
            // txttablenum
            // 
            this.txttablenum.Location = new System.Drawing.Point(204, 198);
            this.txttablenum.Name = "txttablenum";
            this.txttablenum.Size = new System.Drawing.Size(577, 35);
            this.txttablenum.TabIndex = 11;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(28, 202);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(178, 24);
            this.label6.TabIndex = 10;
            this.label6.Text = "预约餐桌编号：";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(45, 280);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(706, 142);
            this.button1.TabIndex = 14;
            this.button1.Text = "点菜";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // FrmORDER
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(804, 473);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.txttablenum);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.txtSex);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.txtName);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txtTel);
            this.Controls.Add(this.label3);
            this.Name = "FrmORDER";
            this.Text = "点菜";
            this.Load += new System.EventHandler(this.FrmORDER_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox txtTel;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtName;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtSex;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txttablenum;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button button1;
    }
}