using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;

namespace WCFServiceHost
{
    class Program
    {
        static void Main()
        {
            using (ServiceHost server = new ServiceHost(typeof(WCFDiffContract.WCFGetMessageService))) {
                server.Open();

                Console.WriteLine("Server started @ " + DateTime.Now.ToString());
                Console.ReadLine();
            }
        }
    }
}
