using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Newtonsoft.Json;

namespace JsonTools
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (tbtext.Text == "")
            {
                MessageBox.Show("请输入文本内容！");
                tbtext.Focus();
                return;
            }
            tbjson.Text = JsonConvert.SerializeObject(tbtext.Text);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (tbjson.Text == "")
            {
                MessageBox.Show("请输入JSOn文本内容！");
                tbjson.Focus();
                return;
            }
            tbtext.Text = (String)JsonConvert.DeserializeObject(tbjson.Text);
        }
    }
}
