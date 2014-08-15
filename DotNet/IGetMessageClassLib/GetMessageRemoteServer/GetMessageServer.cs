using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;

namespace GetMessageRemoteServer
{
    class GetMessageServer
    {
        static void Main()
        {
            GetMessageClassLib.GetMessage serverMsg = new GetMessageClassLib.GetMessage();

            TcpChannel channel = new TcpChannel(8090);
            ChannelServices.RegisterChannel(channel, true);
            RemotingConfiguration.RegisterWellKnownServiceType(typeof(GetMessageClassLib.GetMessage), "getMessage", WellKnownObjectMode.Singleton);

            Console.WriteLine("Remote server started @ " + DateTime.Now);
            Console.ReadLine();
        }
    }
}
