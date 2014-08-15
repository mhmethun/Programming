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
        public ClientForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            WCFGetMessageServerService.WCFGetMessageServiceClient client = new WCFGetMessageServerService.WCFGetMessageServiceClient("BasicHttpBinding_IWCFGetMessageService");
            nameLabel.Text = client.getMessage(nameTextBox.Text);

        }
    }
}
