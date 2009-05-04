package mt;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dmagiserver.web.admin.backend.DmAgiAdminUtil;
import org.osgi.framework.BundleContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class AdminAppController extends AbstractController {
	BundleContext bundleContext;
	public AdminAppController(BundleContext bc) {
		bundleContext=bc;
	}
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		
		List<Properties> servers =util.getServersProperties();
		ModelAndView mv = new ModelAndView();
		mv.addObject("serversList",servers);
		
		mv.setViewName("Admin");
		return mv;
		
	}

}
