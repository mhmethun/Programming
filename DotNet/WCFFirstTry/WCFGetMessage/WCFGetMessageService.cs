using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WCFGetMessage
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "WCFGetMessageService" in both code and config file together.
    public class WCFGetMessageService : IWCFGetMessageService
    {

        #region IWCFGetMessageService Members

        public string getMessage(string name)
        {
            return "Hello " + name;
        }

        #endregion
    }
}
