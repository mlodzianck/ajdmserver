package org.dmagiserver.server;

import java.util.Properties;

import org.asteriskjava.fastagi.MappingStrategy;

public interface IAgiServer {
	public void startServer();
	public boolean isRunning();
	public void stopServer();
	public void setMappingStrategy(MappingStrategy strategy);
	static String SERVER_PORT = "serverPort";
	static String SERVER_DOMAIN="serverDomain";
	static String SERVER_POOL_SIZE="poolSize";
	static String SERVER_MAX_POOL_SIZE="maxPoolSize";

	
	/**
	 * Getter for server propery
	 * Properties contain informations such domain, port and poll size
	 * of AgiServer
	 * @param key name of Property
	 * @return value of property or null if not exist
	 */
	public String getProperty(String key);
	
	
	/**
	 * Getter for all server's properies
	 * @return 
	 */
	public Properties getProperties();

	public void setProperties(Properties p);
}
