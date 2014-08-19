using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace HTTPClient
{
    public partial class HTTPForm : System.Web.UI.Page
    {
        private WCFGetMsgHostService.WCFGetHTTPMessageServiceClient hostService;

        protected void Page_Load(object sender, EventArgs e)
        {
            this.hostService = new WCFGetMsgHostService.WCFGetHTTPMessageServiceClient();
        }

        protected void nameButton_Click(object sender, EventArgs e)
        {
            String name = nameTextBox.Text;
            nameLabel.Text = this.hostService.getPublicMessage(name);
        }
    }
}