package org.dmagiserver.server.manager;

import java.util.List;
import java.util.Properties;

import org.dmagiserver.server.IAgiServer;

public interface IServerManager {
	public List<IAgiServer> getServers();
	public List<Properties> getServersProperties();
	public boolean addServer(Properties serverProperies, boolean autorun);
	public boolean removeServer(String filter);
	
	

}
