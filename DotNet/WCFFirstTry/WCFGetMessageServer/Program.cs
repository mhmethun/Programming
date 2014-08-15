using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;

namespace WCFGetMessageServer
{
    class Program
    {
        static void Main()
        {
            using(ServiceHost server = new ServiceHost(typeof(WCFGetMessage.WCFGetMessageService))){
                try
                {
                    server.Open();
                }
                catch (CommunicationException ex)
                {
                    Console.WriteLine("Error in open server connection: " + ex.Message.ToString());
                }
                finally {
                    Console.WriteLine("Server is starting @ " + DateTime.Now.ToString());
                    Console.ReadLine();
                }
               
            }
        }
    }
}
