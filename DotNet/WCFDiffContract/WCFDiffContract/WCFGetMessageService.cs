using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WCFDiffContract
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "WCFGetMessageService" in both code and config file together.
    public class WCFGetMessageService : IWCFGetHTTPMessageService, IWCFGetTCPMessageService
    {

        #region IWCFGetHTTPMessageService Members

        public string getPublicMessage(string name)
        {
            return "Hi, " + name + ". This is a public zoon.";
        }

        #endregion

        #region IWCFGetTCPMessageService Members

        public string getPrivateMessage(string name)
        {
            return "Hi, " + name + ". This is a private zoon.";
        }

        #endregion
    }
}
