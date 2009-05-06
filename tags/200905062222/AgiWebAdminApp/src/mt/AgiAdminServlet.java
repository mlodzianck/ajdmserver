package mt;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mt.managment.ServerManagment;

import org.dmagiserver.server.starter.IStarter;
import org.dmagiserver.stats.IScriptStat;
import org.dmagiserver.stats.IScriptStats;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AgiAdminServlet extends SimpleFormController {
	private BundleContext bc;

	IStarter starter;

	public AgiAdminServlet(BundleContext bc) {
		this.bc = bc;
	}

	public void setStarter(IStarter starter) {
		this.starter = starter;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// System.out.println("REINSTALLING SERVERS");
		// starter.reinstallServers();

		LinkedList<String> o = new LinkedList<String>();
		String ouput = "";
		ServiceReference[] refs = bc.getAllServiceReferences(null, null);
		for (int i = 0; i < refs.length; i++) {
			ServiceReference ref = refs[i];
			String s1 = "Service in bundle id " + ref.getBundle().getBundleId()
					+ " named " + ref.getBundle().getSymbolicName() + "<br>";
			String s2 = "Props<br>";
			String[] props = ref.getPropertyKeys();
			s2 += "We 've got " + props.length + " props <br>";
			String s3 = "";
			try {
				for (int j = 0; j < props.length; j++) {
					s3 += "Prop " + props[j] + " value "
							+ (ref.getProperty(props[j]) instanceof String?(String)ref.getProperty(props[j]):ref.getProperty(props[j])) + "<br>";
				}
			} catch (Exception e) {

			}
			//ouput += s1 + s2 + s3;
			o.add(s1 + s2 + s3);
		}

		ouput += "<br><br>++++++FILTER BELOW++++++<BR>";
		refs = bc.getAllServiceReferences(null, "(agiReqestPattern=*)");
		if (refs != null) {
			ouput += "got " + refs.length + " services<BR>";
			for (int i = 0; i < refs.length; i++) {
				ServiceReference ref = refs[i];
				String s1 = "Service in bundle id "
						+ ref.getBundle().getBundleId() + " named "
						+ ref.getBundle().getSymbolicName() + "<br>";
				String s2 = "Props<br>";
				String[] props = ref.getPropertyKeys();
				s2 += "We 've got " + props.length + " props <br>";
				String s3 = "";
				try {
					for (int j = 0; j < props.length; j++) {
						s3 += "Prop " + props[j] + " value "
								+ (String)ref.getProperty(props[j]) + "<br>";
					}
				} catch (Exception e) {

				}
				//ouput += s1 + s2 + s3 + "<br><br>";
				o.add(s1 + s2 + s3);
			}
		}
		System.out.println("AgiAdminServlet.handleRequestInternal()");
		ServerManagment m = new ServerManagment(this.bc);
		ModelAndView mv = new ModelAndView();
		mv.addObject("val", m.getServersProperties());
		mv.addObject("list", o);
		mv.setViewName("home");

		ServiceReference ref = bc
				.getServiceReference(org.dmagiserver.stats.IScriptStats.class
						.getCanonicalName());
		if (ref != null) {
			IScriptStats stats = (IScriptStats) bc.getService(ref);
			String scriptsList = "";
			System.err.println("Reference found-proceeding");
			String qString = arg0.getQueryString();
			if (qString != null) {
				scriptsList += "Got qString " + qString + " <br>";
				String[] parts = qString.split("=");

				if (parts.length > 0) {
					scriptsList += "Got " + parts.length + " parts <br>";
					IScriptStat script = stats.getScriptStat(parts[1].trim());
					scriptsList += "Killing <pre>" + parts[1] + "</pre><br>";
					if (script != null) {
						script.getExecutionThread().stop();
					}
				}
			}

			List<String> scripts = stats.getRunningScriptsIDs();

			Iterator<String> itr = scripts.iterator();
			while (itr.hasNext()) {
				String string = (String) itr.next();
				scriptsList += string + "<br>";
				scriptsList += "<a href=\"" + arg0.getRequestURI() + "?kill="
						+ string + "\">KILL</A>" + "<br>";
			}
			// scriptsList+=arg0.getQueryString()+"<br>";
			// scriptsList+=arg0.getPathTranslated()+"<br>";
			// scriptsList+=arg0.getContextPath()+"<br>";
			// scriptsList+=arg0.getServletPath()+"<br>";

			mv.addObject("scriptlist", scriptsList);
		}

		return mv;

	}
}
