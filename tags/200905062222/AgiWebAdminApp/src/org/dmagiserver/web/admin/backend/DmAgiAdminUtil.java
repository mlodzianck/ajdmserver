package org.dmagiserver.web.admin.backend;

import java.util.List;
import java.util.Properties;

import org.dmagiserver.server.manager.IServerManager;
import org.dmagiserver.server.starter.IStarter;
import org.dmagiserver.stats.IScriptStats;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class DmAgiAdminUtil {
	BundleContext bundleContext;

	public DmAgiAdminUtil(BundleContext bc) {
		bundleContext = bc;
	}

	public IServerManager getServerManagerService() {
		ServiceReference ref = bundleContext
				.getServiceReference(IServerManager.class.getName());
		if (ref == null) {
			System.err.println("Cannot import ServerManager service ");
			return null;
		}
		return (IServerManager) bundleContext.getService(ref);

	}

	public IStarter getStarterService() {
		ServiceReference ref = bundleContext.getServiceReference(IStarter.class
				.getName());
		if (ref == null) {
			System.err.println("Cannot import Starter service ");
			return null;
		}
		return (IStarter) bundleContext.getService(ref);

	}

	public List<Properties> getServersProperties() {
		IServerManager manager = getServerManagerService();
		if (manager != null) {
			return manager.getServersProperties();
		} else {
			return null;
		}
	}

	public ServiceReference[] getScriptsReferencesForDomain(String domainName) {
		ServiceReference[] refs;
		try {
			refs = bundleContext.getAllServiceReferences(
					null, "(agiDomain=" + domainName + ")");
			
			return refs;
		} catch (InvalidSyntaxException e) {
			System.err.println("Exception while impoertig scripts refereces");
			return null;
		}
		
		
	}
	
	public ServiceReference[] getScriptsReferences() {
		ServiceReference[] refs;
		try {
			refs = bundleContext.getAllServiceReferences(
					null, "(agiDomain=*)");
			for (int i = 0; i < refs.length; i++) {
				
			}
			return refs;
		} catch (InvalidSyntaxException e) {
			System.err.println("Exception while impoertig scripts refereces");
			return null;
		}
		
		
	}
	
	
	
	public IScriptStats getScriptStatsService() {
		ServiceReference ref = bundleContext
		.getServiceReference(org.dmagiserver.stats.IScriptStats.class
				.getCanonicalName());
		if (ref!=null) {
			return (IScriptStats)bundleContext.getService(ref);
		} else {
			System.err.println("Cannot import IScriptStats service ");
			return null;
		}
	}

}
