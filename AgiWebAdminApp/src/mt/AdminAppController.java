package mt;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.asteriskjava.fastagi.AgiRequest;
import org.dmagiserver.server.IAgiServer;
import org.dmagiserver.server.manager.IServerManager;
import org.dmagiserver.stats.IScriptStat;
import org.dmagiserver.stats.IScriptStats;
import org.dmagiserver.web.admin.backend.DmAgiAdminUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AdminAppController extends MultiActionController {
	BundleContext bundleContext;
	public AdminAppController(BundleContext bc) {
		bundleContext=bc;
	}
	
	
	
	public ModelAndView getServers(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		
		List<Properties> servers =util.getServersProperties();
		ModelAndView mv = new ModelAndView();
		mv.addObject("serversList",servers);
		mv.addObject("pageTitle","Configured servers");

		mv.addObject("contentPage", "serverListContent.jsp");
		mv.setViewName("template");
		return mv;
		
	}
	
	
	public ModelAndView removeServer(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IServerManager manager=util.getServerManagerService();
		String domain=arg0.getParameter("domain");
		List<IAgiServer> server=manager.getServers();
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("pageTitle","Removing server for domain "+domain);
		

		mv.addObject("contentPage", "informationContent.jsp");
		mv.setViewName("template");
		
		
		
		for (Iterator iterator = server.iterator(); iterator.hasNext();) {
			IAgiServer agiServer = (IAgiServer) iterator.next();
			if (agiServer.getProperty(IAgiServer.SERVER_DOMAIN).equals(domain)) {
				manager.removeServer(domain);
				mv.addObject("info", "Server removed");
				mv.addObject("additionalInfo", "Remember! If you want to permanently delete the delete the server's definition from agi-servers.xml file");
				
				return mv;

			}
			
		}
		mv.addObject("info", "Server for domain "+domain+" does not exist");
		return mv;

		
		
	}
	
	public ModelAndView restartServer(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IServerManager manager=util.getServerManagerService();
		String domain=arg0.getParameter("domain");
		List<IAgiServer> server=manager.getServers();
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("pageTitle","Restarting server for domain "+domain);
		

		mv.addObject("contentPage", "informationContent.jsp");
		mv.setViewName("template");
		
		
		
		for (Iterator iterator = server.iterator(); iterator.hasNext();) {
			IAgiServer agiServer = (IAgiServer) iterator.next();
			if (agiServer.getProperty(IAgiServer.SERVER_DOMAIN).equals(domain)) {
				agiServer.stopServer();
				agiServer.startServer();
				mv.addObject("info", "Server restarter");
				return mv;

			}
			
		}
		mv.addObject("info", "Server for domain "+domain+" does not exist");
		return mv;

		
		
	}
	
	
	
	
	
	
	public ModelAndView getScripts(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		ServiceReference[] scripts=util.getScriptsReferences();
		
		
		List<Properties> scriptsList =new LinkedList<Properties>();
		for (int i = 0; i < scripts.length; i++) {
			Properties props=new Properties();
			props.put("Server Domain",(String)scripts[i].getProperty("agiDomain"));
			props.put("Request pattern",(String)scripts[i].getProperty("agiRequestPattern"));
			props.put("Last modified",(new Date(scripts[i].getBundle().getLastModified())).toString());
			props.put("Deployed  in bundle ",scripts[i].getBundle().getSymbolicName());
			String[] additionalProps=scripts[i].getPropertyKeys();
			for (int j = 0; j < additionalProps.length; j++) {
				String additionalProp = additionalProps[j];
				if (additionalProp.equals("agiDomain") || additionalProp.equals("agiRequestPattern")) {
					continue;
				
				}
				props.put("[System] "+additionalProp, scripts[i].getProperty(additionalProp));
				
			}
			
			scriptsList.add(props);

			
		
			
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("scriptsList",scriptsList);
		mv.addObject("pageTitle","Configured scripts");

		mv.addObject("contentPage", "configuredScriptsListContent.jsp");
		mv.setViewName("template");
		return mv;
		
	}
	public ModelAndView getScriptsForDomain(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView();

		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		String domain=arg0.getParameter("domain");
		ServiceReference[] scripts=util.getScriptsReferencesForDomain(domain);
		
		
		List<Properties> scriptsList =new LinkedList<Properties>();
		if (scripts==null) {
			mv.addObject("pageTitle","Configured scripts for domain "+domain);
			mv.addObject("info", "No scripts configured");

			mv.addObject("contentPage", "informationContent.jsp");
			mv.setViewName("template");
			return mv;
		}
		for (int i = 0; i < scripts.length; i++) {
			Properties props=new Properties();
			props.put("Server Domain",(String)scripts[i].getProperty("agiDomain"));
			props.put("Request pattern",(String)scripts[i].getProperty("agiRequestPattern"));
			props.put("Last modified",(new Date(scripts[i].getBundle().getLastModified())).toString());
			props.put("Deployed  in bundle ",scripts[i].getBundle().getSymbolicName());
			String[] additionalProps=scripts[i].getPropertyKeys();
			for (int j = 0; j < additionalProps.length; j++) {
				String additionalProp = additionalProps[j];
				if (additionalProp.equals("agiDomain") || additionalProp.equals("agiRequestPattern")) {
					continue;
				
				}
				props.put("Additional prop "+additionalProp, scripts[i].getProperty(additionalProp));
				
			}
			
			scriptsList.add(props);

			
		}
		mv.addObject("scriptsList",scriptsList);
		mv.addObject("pageTitle","Configured scripts for domain "+domain);

		mv.addObject("contentPage", "configuredScriptsListContent.jsp");
		mv.setViewName("template");
		return mv;
		
	}
	
	
	
	
	public ModelAndView defaultHandleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageTitle","Home");
		mv.addObject("contentPage", "homeContent.jsp");
		mv.setViewName("template");
		return mv;
		
	}
	
	
	
	public ModelAndView reinstallServers(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		util.getStarterService().reinstallServers();
		ModelAndView mv = new ModelAndView();

		mv.addObject("pageTitle","Reinstalling all servers");
		

		mv.addObject("contentPage", "informationContent.jsp");
		mv.setViewName("template");
		return mv;
	}

	public ModelAndView getRunningScriptsForDomain(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IScriptStats stats=util.getScriptStatsService();
		String domain=httpServletRequest.getParameter("domain");
		List<String> scriptsIds = stats.getRunningScriptsIDsForDomain(domain);
		List<Properties> scripts=new LinkedList<Properties>();
		Iterator<String> itr=scriptsIds.iterator();
		while (itr.hasNext()) {
			Properties props=new Properties();
			String scriptID = (String) itr.next();
			props.put("Script ID",scriptID);
			IScriptStat stat=stats.getScriptStat(scriptID);
			props.put("Script class",stat.getHandlingClass().getCanonicalName());
			props.put("Asterisk host",stat.getRequest().getRemoteAddress().getCanonicalHostName());
			Date startDate=stat.getStartDate();
			long runningSeconds= ((new Date()).getTime()-startDate.getTime())/1000;
			props.put("Start time ",startDate.toString()+" (running from "+runningSeconds+" sec.)");
			props.put("Agi script",stat.getRequest().getScript());
			props.put("Agi URI",stat.getRequest().getRequestURL());
			scripts.add(props);
			
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageTitle","Running scripts ");
		mv.addObject("scriptsList",scripts);
	

		mv.addObject("contentPage", "runningScriptsListContent.jsp");
		mv.setViewName("template");
		return mv;
	}
	
	public ModelAndView getRunningScripts(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IScriptStats stats=util.getScriptStatsService();
		List<String> scriptsIds = stats.getRunningScriptsIDs();
		List<Properties> scripts=new LinkedList<Properties>();
		Iterator<String> itr=scriptsIds.iterator();
		while (itr.hasNext()) {
			Properties props=new Properties();
			String scriptID = (String) itr.next();
			props.put("Script ID",scriptID);
			IScriptStat stat=stats.getScriptStat(scriptID);
			props.put("Script class",stat.getHandlingClass().getCanonicalName());
			props.put("Asterisk host",stat.getRequest().getRemoteAddress().getCanonicalHostName());
			Date startDate=stat.getStartDate();
			long runningSeconds= ((new Date()).getTime()-startDate.getTime())/1000;
			props.put("Start time ",startDate.toString()+" (running from "+runningSeconds+" sec.)");
			props.put("Agi script",stat.getRequest().getScript());
			props.put("Agi URI",stat.getRequest().getRequestURL());
			scripts.add(props);
			
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageTitle","Running scripts ");
		mv.addObject("scriptsList",scripts);
	

		mv.addObject("contentPage", "runningScriptsListContent.jsp");
		mv.setViewName("template");
		return mv;
	}
	
	
	public ModelAndView killScript(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IScriptStats stats=util.getScriptStatsService();
		String scriptID=httpServletRequest.getParameter("scriptid");
		IScriptStat scriptStat=stats.getScriptStat(scriptID);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageTitle","Killing script "+scriptID);

		mv.addObject("contentPage", "informationContent.jsp");
		mv.setViewName("template");
		if (scriptStat==null) {
			mv.addObject("info", "Script "+scriptID+" not found");
			return mv;
		}
		scriptStat.getExecutionThread().stop();
		mv.addObject("info", "Script "+scriptID+" killed");
		mv.addObject("additionalInfo", "This is experimental feature");

		return mv;
	}
	
	public ModelAndView getRunningScriptDetail(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		DmAgiAdminUtil util=new DmAgiAdminUtil(bundleContext);
		IScriptStats stats=util.getScriptStatsService();
		String scriptID=httpServletRequest.getParameter("scriptid");
		IScriptStat scriptStat=stats.getScriptStat(scriptID);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageTitle","Script detail "+scriptID);

		
		mv.setViewName("template");
		if (scriptStat==null) {
			mv.addObject("contentPage", "informationContent.jsp");
			mv.addObject("info", "Script "+scriptID+" not found");
			return mv;
		}
		
		mv.addObject("contentPage", "runningScriptsDetailContent.jsp");
		HashMap<String, String> props=new HashMap<String, String>();
		props.put("Server domain", scriptStat.getServerDomain());
		props.put("Start Date", scriptStat.getStartDate().toString());
		props.put("Execution thread Name", scriptStat.getExecutionThread().getName());
		props.put("Local address", ""+scriptStat.getRequest().getLocalAddress().getHostAddress());
		props.put("Local Port ", ""+scriptStat.getRequest().getLocalPort());
		props.put("Remote Host ", scriptStat.getRequest().getRemoteAddress().getHostAddress());
		props.put("Remote Port ", ""+scriptStat.getRequest().getRemotePort());


		AgiRequest request=scriptStat.getRequest();
		Map<String, String[]> reqParams=request.getParameterMap();
		Iterator<String> itr=reqParams.keySet().iterator();
		while (itr.hasNext()) {
			String paramName = (String) itr.next();
		
			String[] params=request.getParameterValues(paramName);
			for (int i = 0; i < params.length; i++) {
				String param = params[i];
				props.put(i+" "+paramName, param);
				
			}
			
		}
		Properties rawRequestProp=new Properties();
		Map<String, String> rawRequest=request.getRequest();
		Iterator<String> itr2=rawRequest.keySet().iterator();
		while (itr2.hasNext()) {
			String paramName = (String) itr2.next();
			
				rawRequestProp.put(paramName, rawRequest.get(paramName));
				
			
			
		}
		mv.addObject("scriptRaw", rawRequestProp);
		mv.addObject("script",props);

		return mv;
	}
	
}
