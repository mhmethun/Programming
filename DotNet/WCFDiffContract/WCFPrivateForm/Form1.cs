using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WCFPrivateForm
{
    public partial class Form1 : Form
    {
        private WCFPrivateServiceRef.WCFGetTCPMessageServiceClient privateClient;
        public Form1()
        {
            InitializeComponent();

            this.privateClient = new WCFPrivateServiceRef.WCFGetTCPMessageServiceClient();
        }

        private void nameButton_Click(object sender, EventArgs e)
        {
            String name = nameTextBox.Text;
            nameLabel.Text = this.privateClient.getPrivateMessage(name);
        }
    }
}
