package mt;

import org.asteriskjava.fastagi.AgiScript;
import org.dmagiserver.IAgiScriptFactory;

public class TestScriptFactory  implements IAgiScriptFactory{
	public TestScriptFactory() {
		// TODO Auto-generated constructor stub
	}	
	
	public AgiScript getScript() {
			return new TestScript();
		}
		
}
