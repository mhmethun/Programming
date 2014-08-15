using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WCFGetMessageClient
{
    public partial class ClientForm : Form
    {
        private String[] communicationMedia = new String[2];
        private int selectionIndex = 0;

        public ClientForm()
        {
            InitializeComponent();

            this.communicationMedia[0] = "BasicHttpBinding_IWCFGetMessageService";
            this.communicationMedia[1] = "NetTcpBinding_IWCFGetMessageService";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.selectionIndex >= this.communicationMedia.Length) this.selectionIndex = 0;

            WCFGetMessageServerService.WCFGetMessageServiceClient client = new WCFGetMessageServerService.WCFGetMessageServiceClient(this.communicationMedia[this.selectionIndex]);
            nameLabel.Text = client.getMessage(nameTextBox.Text);
        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            this.selectionIndex = 0;
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            this.selectionIndex = 1;
        }
    }
}
