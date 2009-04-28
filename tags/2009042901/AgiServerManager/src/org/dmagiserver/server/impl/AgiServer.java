package org.dmagiserver.server.impl;

import java.io.IOException;
import java.util.Properties;

import org.asteriskjava.fastagi.ClassNameMappingStrategy;
import org.asteriskjava.fastagi.DefaultAgiServer;
import org.asteriskjava.fastagi.MappingStrategy;
import org.dmagiserver.server.IAgiServer;

public class AgiServer extends DefaultAgiServer implements IAgiServer, Runnable{
		private Properties serverProps=new Properties();
		private Thread serverThread;
		private MappingStrategy mappingStrategy;
	
		@Override
		public String getProperty(String key) {
			return serverProps.getProperty(key);
		}
		@Override
		public Properties getProperties() {
				return serverProps;
		}
		@Override
		public boolean isRunning() {
			return serverThread.isAlive();
		}
		@Override
		public void run() {
			try {
				setPort(Integer.parseInt(serverProps.getProperty((IAgiServer.SERVER_PORT))));
				setMaximumPoolSize(Integer.parseInt(serverProps.getProperty((IAgiServer.SERVER_MAX_POOL_SIZE))));
				setPoolSize(Integer.parseInt(serverProps.getProperty((IAgiServer.SERVER_PORT))));
				startup();
			} catch (IllegalStateException e) {
				System.err.println("Exception while starting server: "+e);
			} catch (IOException e) {
				System.err.println("Exception while starting server: "+e);
			}
	
		}
		@Override
		public void startServer() {
			serverThread=new Thread(this);
			serverThread.start();
		
		}
		
		public void stopServer() {
			shutdown();
			
		};
		
		
		 
		
		@Override
		public void setProperties(Properties p) {
			this.serverProps=p;
		
		}
		
		
		
		
		
		
		
}
