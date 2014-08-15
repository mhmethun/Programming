using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WCFFirstTry
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IWCFMessageService" in both code and config file together.
    [ServiceContract]
    public interface IWCFMessageService
    {
        [OperationContract]
        String getMessage(String name);
    }
}
