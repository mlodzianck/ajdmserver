package mt;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dmagiserver.stats.IScriptStats;
import org.dmagiserver.web.admin.backend.DmAgiAdminUtil;
import org.osgi.framework.BundleContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AdminAppController extends MultiActionController {
	BundleContext bundleContext;
	public AdminAppController(BundleContext bc) {
		bundleContext=bc;
	}
	
	
	
	public ModelAndView defaultHandleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		
		List<Properties> servers =util.getServersProperties();
		ModelAndView mv = new ModelAndView();
		mv.addObject("serversList",servers);
		
		mv.setViewName("serverlist");
		return mv;
		
	}
	
	
	
	public ModelAndView reinstallServers(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		util.getStarterService().reinstallServers();
		ModelAndView mv = new ModelAndView();
		mv.addObject("text","actionn: reinstallServers-- servers are being reinstalled");
		
		mv.setViewName("serverlist");
		return mv;
	}

	public ModelAndView getRunningScripts(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IScriptStats stats=util.getScriptStatsService();
		String domain=httpServletRequest.getParameter("domain");
		List<String> scripts = stats.getRunningScriptsIDsForDomain(domain);
		ModelAndView mv = new ModelAndView();
		mv.addObject("text","actionn: getRunningScripts for domain "+domain);
		mv.addObject("serversList",httpServletRequest.getParameterMap());
		mv.setViewName("serverlist");
		return mv;
	}
}
