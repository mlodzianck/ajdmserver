package mt;

import org.asteriskjava.fastagi.AgiScript;
import org.dmagiserver.IAgiScriptFactory;

public class TestScriptFactory  implements IAgiScriptFactory{
		public AgiScript getScript() {
			return new TestScript();
		}
}
