package mt.managment;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dmagiserver.server.IAgiServer;
import org.dmagiserver.server.manager.IServerManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServerManagment {
	BundleContext bc;

	public ServerManagment(BundleContext bc) {
		this.bc = bc;

	}

	public String getServersProperties() {
			ServiceReference ref=bc.getServiceReference(IServerManager.class.getName());
			if (ref==null) {
				System.err.println("Cannot import service ");
				return null;
			} 
			IServerManager manager=(IServerManager)bc.getService(ref);
			List<Properties> servers=manager.getServersProperties();
			Iterator<Properties> itr=servers.iterator();
			String ret="<table><tr><td>Servers List</td></tr>";
			while (itr.hasNext()) {
				ret+="<tr><td>";
				Properties properties = (Properties) itr.next();
				String serverDomain=properties.getProperty(IAgiServer.SERVER_DOMAIN);
				String serverPort=properties.getProperty(IAgiServer.SERVER_PORT);
				ret+="<b>Server domain</b> "+serverDomain+"<br>Server Port "+serverPort;
				ret+="</td></tr>";
				
			}
			ret+="</table>";
			return ret;
			
			
			
		}
}
