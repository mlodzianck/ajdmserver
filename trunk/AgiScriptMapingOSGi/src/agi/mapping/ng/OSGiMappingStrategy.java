package agi.mapping.ng;

import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.MappingStrategy;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class OSGiMappingStrategy implements MappingStrategy {
	BundleContext bContext;

	public OSGiMappingStrategy(BundleContext bc) {
		bContext = bc;
	}

	@Override
	public AgiScript determineScript(AgiRequest arg0) {
		try {
			ServiceReference[] refs = bContext.getAllServiceReferences(null,
					"(agiRequestPattern=*)");
			for (int i = 0; i < refs.length; i++) {
				ServiceReference ref = refs[i];
				System.err.println("Got service from bundle "
						+ ref.getBundle().getSymbolicName());
				String reqPattern = (String) ref
						.getProperty("agiRequestPattern");
				System.err.println("Got pattern " + reqPattern);
				if (reqPattern != null) {
					if (arg0.getRequestURL().matches(reqPattern)) {
						System.err
								.println("Ok it matches- I  return script here");
						return (AgiScript)bContext.getService(ref);
					}
				}

			}
		} catch (Exception e) {
			System.err.println("Oh, man exception while obtaining services "
					+ e);
		}
		return null;
	}

}
