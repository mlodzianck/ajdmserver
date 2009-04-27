package mt;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

public class TestScript implements AgiScript, ServiceFactory {
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
	public Object getService(Bundle arg0, ServiceRegistration arg1) {
		return new TestScript();
	}
	
	
	public TestScript getBean() {
		return new TestScript();
	}
	@Override
	public void ungetService(Bundle arg0, ServiceRegistration arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

}
