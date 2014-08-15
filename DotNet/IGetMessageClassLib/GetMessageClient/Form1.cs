using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;

namespace GetMessageClient
{
    public partial class Form1 : Form
    {
        IGetMessageClassLib.IGetMessage clientMsg;

        public Form1()
        {
            InitializeComponent();

            TcpChannel channel = new TcpChannel();
            ChannelServices.RegisterChannel(channel, true);
            clientMsg = (IGetMessageClassLib.IGetMessage)Activator.GetObject(typeof(IGetMessageClassLib.IGetMessage), "tcp://localhost:8090/getMessage");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            nameLabel.Text = clientMsg.getMessage(nameTextBox.Text);
        }
    }
}
