using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GetMessageClassLib
{
    public class GetMessage : MarshalByRefObject, IGetMessageClassLib.IGetMessage
    {

        #region IGetMessage Members

        public string getMessage(string name)
        {
            return "Hello " + name;
        }

        #endregion
    }
}
