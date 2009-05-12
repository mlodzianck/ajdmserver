package org.dmagiserver.starter.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.dmagiserver.server.IAgiServer;
import org.dmagiserver.server.manager.IServerManager;
import org.dmagiserver.server.starter.IStarter;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLServerStarter implements IStarter{
	private Resource confFileUnix;
	private Resource confFileWin;
	private Resource confFile;
	private IServerManager serverManager;
	
	
	
	
	
	List<Properties> servers=new LinkedList<Properties>();

	public void setServerManager(
			IServerManager serverManagerReference) {
		this.serverManager = serverManagerReference;
	}
	
	
	public void setConfFileUnix(Resource confFileUnix) {
		this.confFileUnix = confFileUnix;
	}
	public void setConfFileWin(Resource confFileWin) {
		this.confFileWin = confFileWin;
	}

	public XMLServerStarter() {
		System.out.println("XMLServerStarter.XMLServerStarter()");
	}

	public void installServers() {
		if (confFileUnix.exists()) {
			System.out.println("XML file exist under Unix location.");
			confFile=confFileUnix;
		} else 
		if (confFileWin.exists()) {
			System.out.println("XML file exist under Windows location.");
			confFile=confFileWin;
		} else {
			System.err.println("No agi-servers.xml file found. Aborting server installation");
			return;
		}
		try {
			
			System.err.println("Processing file "
					+ confFile.getFile().getAbsolutePath());
			

		} catch (IOException e) {
			System.err.println("Exception while openning xml file");
			e.printStackTrace();
			return;
		}
		parseXMLFile();
		runServers();
	}

	private void parseXMLFile() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc=builder.parse(confFile.getInputStream());
			System.err.println("XML Parsed");
			XPathFactory xpathFactory= XPathFactory.newInstance();
			XPath xpath=xpathFactory.newXPath();
			XPathExpression expression=xpath.compile("servers/server");
			Object result = expression.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
				
			  NodeList childList=nodes.item(i).getChildNodes();
			  Properties props=new Properties();
			  for (int j= 0; j <childList.getLength();j++) {
				  String name=childList.item(j).getLocalName();
				  String value=childList.item(j).getTextContent();
				  if (name!=null && value!=null) {
					  props.put(name, value);
					  System.err.println("Adding "+name+"|"+value+" to props");
				  }
				  
			  }
			  servers.add(props);
			}
		} catch (Exception e) {
			
			System.err.println("Exception while parsing XML file. Check syntax! "+e);
			
		}

	}
	
	
	private void runServers() {
		if (serverManager!=null) {
			System.err.println("ref is "+serverManager);
			IServerManager manager=this.serverManager;
			for (Iterator iterator = servers.iterator(); iterator.hasNext();) {
				Properties p=(Properties)iterator.next();
				manager.addServer(p, true);
				
			}
		} else {
			System.err.println("ServerManager service not found. Did you deploy all bundles??");
		}
		
		
	}


	@Override
	public void reinstallServers() {
		uninstallServers();
		installServers();
		
	}


	@Override
	public void uninstallServers() {
		System.err.println("Uninstalling all servers configured at previous install");
		for (Iterator iterator = servers.iterator(); iterator.hasNext();) {
			Properties props = (Properties) iterator.next();
			if (props.containsKey(IAgiServer.SERVER_DOMAIN)) {
				System.err.println("Uninstalling server with domain "+props.getProperty(IAgiServer.SERVER_DOMAIN));
				serverManager.removeServer(props.getProperty(IAgiServer.SERVER_DOMAIN));
			}
			
		}
		
	}
	
	
	
}
