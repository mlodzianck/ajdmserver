package mt;

import java.util.Properties;

import org.dmagiserver.server.IAgiServer;
import org.dmagiserver.server.manager.IServerManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class test {
	public test(BundleContext bc) {
		Properties p=new Properties();
		p.put(IAgiServer.SERVER_DOMAIN, "domain1");
		p.put(IAgiServer.SERVER_PORT, "5715");
		IServerManager manager;
		
		ServiceReference ref= bc.getServiceReference("org.dmagiserver.server.manager.IServerManager");
		if (ref!=null) {
			System.err.println("ref is "+ref);
			manager=(IServerManager)bc.getService(ref);
			manager.addServer(p, true);
		}
	}
}
