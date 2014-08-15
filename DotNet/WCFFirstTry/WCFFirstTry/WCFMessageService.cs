using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WCFFirstTry
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "WCFMessageService" in both code and config file together.
    public class WCFMessageService : IWCFMessageService
    {

        #region IWCFMessageService Members

        public string getMessage(string name)
        {
            return "Hello " + name;
        }

        #endregion
    }
}
