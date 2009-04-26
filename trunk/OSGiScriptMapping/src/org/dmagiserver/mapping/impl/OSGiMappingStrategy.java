package org.dmagiserver.mapping.impl;

import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.MappingStrategy;
import org.osgi.framework.BundleContext;
import org.springframework.osgi.context.BundleContextAware;

public class OSGiMappingStrategy implements MappingStrategy, BundleContextAware {
	private String severDomain;
	private BundleContext bundleContext;
	
	
	@Override
	public AgiScript determineScript(AgiRequest arg0) {
		
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setBundleContext(BundleContext arg0) {
			this.bundleContext=arg0;
	}

}
