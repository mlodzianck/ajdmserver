package org.dmagiserver.starter;

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

import org.dmagiserver.server.manager.IServerManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLServerStarter {
	Resource confFile;
	IServerManager serverManager;
	BundleContext bundleContext;
	
	
	
	
	List<Properties> servers=new LinkedList<Properties>();

	public void setServerManager(
			IServerManager serverManagerReference) {
		this.serverManager = serverManagerReference;
	}
	
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	public void setConfFile(Resource confFile) {
		this.confFile = confFile;
	}

	public XMLServerStarter() {
		// TODO Auto-generated constructor stub
	}

	public void install() {

		try {
			System.err.println("Resorce i've got " + confFile.toString());
			System.err.println("Uri is " + confFile.getURI().toString());
			System.err.println("Url is " + confFile.getURL().toString());
			System.err.println("Abs opath "
					+ confFile.getFile().getAbsolutePath());
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parseXMLFile();
		installServers();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}
	
	
	private void installServers() {
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
	
	
	
}
