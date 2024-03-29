package org.dmagiserver.server.manager.impl;

import java.awt.peer.SystemTrayPeer;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dmagiserver.mapping.IOSGiMappingFactory;
import org.dmagiserver.mapping.IOSGiMappingStrategy;
import org.dmagiserver.server.IAgiServer;
import org.dmagiserver.server.impl.AgiServer;
import org.dmagiserver.server.manager.IServerManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.context.BundleContextAware;
import org.springframework.osgi.service.exporter.OsgiServiceRegistrationListener;

public class ServerManager implements IServerManager,
		OsgiServiceRegistrationListener, BundleContextAware {
	static final int DEFAULT_SERVER_MAX_POOL_SIZE = 100;
	static final int DEFAULT_SERVER_POOL_SIZE = 10;

	private HashMap<String, IAgiServer> servers = new HashMap<String, IAgiServer>();
	private BundleContext bc;

	@Override
	synchronized public boolean addServer(Properties serverProperies,
			boolean autorun) {
		try {
			if (!serverProperies.containsKey(IAgiServer.SERVER_DOMAIN)
					|| !serverProperies.containsKey(IAgiServer.SERVER_PORT)) {
				System.err
						.print("No "
								+ IAgiServer.SERVER_DOMAIN
								+ " or "
								+ IAgiServer.SERVER_PORT
								+ " property found\nAt least theese two properties have to be defined\nSERVER WAS NOT ADDED");
				return false;
			}
			String serverid = serverProperies
					.getProperty(IAgiServer.SERVER_DOMAIN);

			if (servers.containsKey(serverid)) {
				System.err.println("Server with domain " + serverid
						+ " already deployed\nRemoving it");
				removeServer(serverid);

			}
			Iterator<IAgiServer> itr = servers.values().iterator();
			while (itr.hasNext()) {
				IAgiServer agiServer = (IAgiServer) itr.next();
				if (agiServer.getProperty(IAgiServer.SERVER_PORT).equals(
						serverProperies.get(IAgiServer.SERVER_PORT))) {
					System.err.println("Port "
							+ serverProperies.get(IAgiServer.SERVER_PORT)
							+ " is used by "
							+ serverProperies
									.getProperty(IAgiServer.SERVER_DOMAIN));
					System.err.println("SERVER NOT DEPLOYED");
					return false;

				}

			}

			if (!serverProperies.containsKey(IAgiServer.SERVER_MAX_POOL_SIZE))
				serverProperies.put(IAgiServer.SERVER_MAX_POOL_SIZE,
						(new Integer(DEFAULT_SERVER_MAX_POOL_SIZE)).toString());
			if (!serverProperies.containsKey(IAgiServer.SERVER_POOL_SIZE))
				serverProperies.put(IAgiServer.SERVER_POOL_SIZE, (new Integer(
						DEFAULT_SERVER_POOL_SIZE)).toString());

			serverProperies.put("DeployTime", (new Date()).toString());
			IAgiServer s = new AgiServer();
			s.setProperties(serverProperies);
			ServiceReference ref = bc
					.getServiceReference("org.dmagiserver.mapping.IOSGiMappingFactory");
			if (ref == null) {
				System.err.println("No mapping strategy found, exitting");
				return false;
			}
			IOSGiMappingFactory mappingStrategyFactory = (IOSGiMappingFactory) bc
					.getService(ref);
			IOSGiMappingStrategy mappingStrategy = mappingStrategyFactory
					.getMappingStrategyForDomain(serverid);
			s.setMappingStrategy(mappingStrategy);
			synchronized (servers) {
				servers.put(serverid, s);
			}
			System.err.println("Server deployed with domainName " + serverid);
			System.err.println("With mapping strategy "
					+ mappingStrategy.toString());
			System.err.println("Port "
					+ serverProperies.getProperty(IAgiServer.SERVER_PORT));
			if (autorun) {
				synchronized (servers) {
					((IAgiServer) servers.get(serverid)).startServer();
				}
				System.err.println("Server started");
			}
			return true;
		} catch (Exception e) {
			System.err.println("Exception while adding server " + e
					+ "\nSERVER WAS NOT ADDED");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	synchronized public List<IAgiServer> getServers() {
		return new LinkedList<IAgiServer>(servers.values());
	}

	@Override
	synchronized public List<Properties> getServersProperties() {
		List<IAgiServer> serversList = getServers();
		if (serversList.size() > 0) {
			LinkedList<Properties> propertiesList = new LinkedList<Properties>();
			synchronized (servers) {
				Iterator<IAgiServer> itr = serversList.iterator();
				while (itr.hasNext()) {
					propertiesList.add(((IAgiServer) itr.next())
							.getProperties());

				}
			}
			return propertiesList;
		} else {
			return null;
		}

	}

	@Override
	synchronized public boolean removeServer(String domain) {
		try {
			IAgiServer oldDomain = servers.get(domain);
			oldDomain.stopServer();
			Thread.sleep(2000);
			System.err.println("Server " + domain + " stopped");
			synchronized (servers) {
				servers.remove(oldDomain.getProperty(IAgiServer.SERVER_DOMAIN));
			}

			Thread.sleep(2000);
			System.err.println("Server " + domain + " removed");
			return true;
		} catch (Exception e) {
			System.err.println("Execption while removing server " + domain);
			e.printStackTrace();
			return false;
		}
	}

	synchronized public void shutdown() {
		Iterator<IAgiServer> itr = servers.values().iterator();
		while (itr.hasNext()) {
			IAgiServer agiServer = (IAgiServer) itr.next();
			removeServer(agiServer.getProperty(IAgiServer.SERVER_DOMAIN));

		}

	}

	@Override
	public void registered(Object arg0, Map arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregistered(Object arg0, Map arg1) throws Exception {
		shutdown();

	}

	@Override
	public void setBundleContext(BundleContext arg0) {
		this.bc = arg0;
	}

}
