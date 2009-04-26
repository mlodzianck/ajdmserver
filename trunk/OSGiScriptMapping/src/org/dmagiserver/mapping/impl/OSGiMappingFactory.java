package org.dmagiserver.mapping.impl;

import org.dmagiserver.mapping.IOSGiMappingFactory;
import org.dmagiserver.mapping.IOSGiMappingStrategy;
import org.osgi.framework.BundleContext;
import org.springframework.osgi.context.BundleContextAware;

public class OSGiMappingFactory implements IOSGiMappingFactory,
		BundleContextAware {

	private BundleContext bc;

	@Override
	public IOSGiMappingStrategy getMappingStrategy() {
		OSGiMappingStrategy s= new OSGiMappingStrategy();
		s.setBundleContext(bc);
		return s;
	}

	@Override
	public IOSGiMappingStrategy getMappingStrategyForDomain(String domain) {
		OSGiMappingStrategy s= new OSGiMappingStrategy();
		s.setBundleContext(bc);
		s.setSeverDomain(domain);
		return s;
	}

	@Override
	public void setBundleContext(BundleContext arg0) {
		this.bc = arg0;
	}

}
