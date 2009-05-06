package mt;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.springframework.osgi.context.BundleContextAware;

public class TestScript implements AgiScript, BundleContextAware{
	public TestScript() {
		super();
		System.out.println("TestScript.TestScript()");
	}
	@Override
	public void service(AgiRequest arg0, AgiChannel arg1) throws AgiException {
		arg1.answer();
		arg1.playMusicOnHold();
		arg1.hangup();

	}
	@Override
	public void setBundleContext(BundleContext arg0) {
		System.err.println("Yuppi, I got bundle context");
		
	}
	

}
