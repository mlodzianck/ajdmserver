package mt;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mt.managment.ServerManagment;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class AgiAdminServlet extends AbstractController {
	private BundleContext bc;

	public AgiAdminServlet(BundleContext bc) {
		this.bc = bc;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
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
							+ ref.getProperty(props[j]) + "<br>";
				}
			} catch (Exception e) {

			}
			ouput += s1 + s2 + s3;
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
								+ ref.getProperty(props[j]) + "<br>";
					}
				} catch (Exception e) {

				}
				ouput += s1 + s2 + s3 + "<br><br>";
				o.add(s1 + s2 + s3);
			}
		}
		System.out.println("AgiAdminServlet.handleRequestInternal()");
		ServerManagment m=new ServerManagment(this.bc);
		ModelAndView mv = new ModelAndView();
		mv.addObject("val", m.getServersProperties());
		mv.addObject("list", o);
		mv.setViewName("home");
		return mv;

	}
}
