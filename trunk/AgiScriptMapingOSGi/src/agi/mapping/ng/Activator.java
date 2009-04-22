package agi.mapping.ng;

import org.asteriskjava.fastagi.MappingStrategy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ServiceRegistration mapingregistration;
	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("Activator.start()");
			MappingStrategy mapping=new OSGiMappingStrategy(arg0);
			
			mapingregistration=arg0.registerService(MappingStrategy.class.getName(), mapping, null);
			System.err.println("Mapping strategy registered");
			
			System.err.println("Registartor registered ");
			
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		mapingregistration.unregister();
		System.out.println("Activator.stop()");

	}
}
