package mt;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dmagiserver.stats.IScriptStat;
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
		List<String> scriptsIds = stats.getRunningScriptsIDsForDomain(domain);
		List<String> scripts=new LinkedList<String>();
		Iterator<String> itr=scriptsIds.iterator();
		while (itr.hasNext()) {
			String scriptID = (String) itr.next();
			String description="<b>Sript ID</b> ::"+scriptID+"<br>";
			IScriptStat stat=stats.getScriptStat(scriptID);
			description+="<ul><li><b>Script class</b> ::"+stat.getHandlingClass().getCanonicalName()+"<br>";
			description+="<li><b>Asterisk host</b> ::"+stat.getRequest().getRemoteAddress().getCanonicalHostName()+"<br>";
			Date startDate=stat.getStartDate();
			long runningSeconds= ((new Date()).getTime()-startDate.getTime())/1000;
			description+="<li><b>Start time </b> ::"+startDate.toString()+" (running from "+runningSeconds+" sec.)<br>";
			description+="<li><b>Agi script </b> ::"+stat.getRequest().getScript()+"<br>";
			description+="<li><b>Agi URI </b> ::"+stat.getRequest().getRequestURL()+"<br></ul>";
			scripts.add(description);
			
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("text","actionn: getRunningScripts for domain "+domain);
		mv.addObject("pageTitle","Running scripts for domain ["+domain+"]");
		mv.addObject("scriptList", scripts);
		mv.setViewName("runningscripts");
		return mv;
	}
}
