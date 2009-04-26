package org.dmagiserver.mapping.impl;

import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.MappingStrategy;
import org.dmagiserver.mapping.IOSGiMappingStrategy;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.context.BundleContextAware;

public class OSGiMappingStrategy implements IOSGiMappingStrategy, BundleContextAware {
	private String severDomain;
	private BundleContext bundleContext;
	
	public OSGiMappingStrategy() {
		
	}
	public OSGiMappingStrategy(BundleContext bc, String servrDomain) {
		this.severDomain = servrDomain;
		this.bundleContext = bc;
	}

	@Override
	public AgiScript determineScript(AgiRequest arg0) {
		System.out.println("OSGiMappingStrategy.determineScript()");
		System.err.println("Looking up for script " + arg0.getScript()
				+ " for domain " + severDomain);
		try {
			ServiceReference[] refs = bundleContext.getAllServiceReferences(
					null, "(agiDomain=" + severDomain + ")");
			for (int i = 0; i < refs.length; i++) {
				ServiceReference ref = refs[i];
				System.err.println("Got service from bundle "
						+ ref.getBundle().getSymbolicName());
				String reqPattern = (String) ref
						.getProperty("agiRequestPattern");
				System.err.println("Got pattern " + reqPattern);
				if (reqPattern != null) {
					if (arg0.getScript().matches(reqPattern)) {
						System.err
								.println("Ok it matches- I  return script here");
						return (AgiScript) bundleContext.getService(ref);
					}
				}

			}
		} catch (InvalidSyntaxException e) {
			System.err.println("Exception while looking up for script " + e);
			return null;
		}
		System.err.println("Nothing found for script " + arg0.getScript());
		return null;
	}

	@Override
	public void setBundleContext(BundleContext arg0) {
		this.bundleContext = arg0;
	}
	
	public void setSeverDomain(String severDomain) {
		this.severDomain = severDomain;
	}

}
