package org.dmagiserver.mapping.impl;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.dmagiserver.stats.IScriptStats;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AgiScriptDecorator implements AgiScript {
	private AgiScript script;
	private AgiRequest request;
	private AgiChannel channel;
	private Thread execThread;
	private BundleContext bundleContext;
	private String statScriptID;
	private String serverDomain;
	
	
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	public void setScript(AgiScript script) {
		this.script = script;
	}
	
	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}

	@Override
	public void service(AgiRequest arg0, AgiChannel arg1) throws AgiException {
		try {

			this.request = arg0;
			this.channel = arg1;
			
			execThread=new Thread(new ScriptExecThread());
			beforeService();
			execThread.start();
			execThread.join();
			afterService();
		} catch (Throwable e) {
			exceptionCathed(e);
		}

	}

	private void beforeService() {
		System.err.println("Begin executing script "
				+ script.getClass().getCanonicalName());
		System.err.println("Script ID " + request.getUniqueId());
		System.err.println("Looking up for "+org.dmagiserver.stats.IScriptStats.class.getCanonicalName()+" service");
		ServiceReference ref= bundleContext.getServiceReference(org.dmagiserver.stats.IScriptStats.class.getCanonicalName());
		if (ref!=null) {
			System.err.println("Reference found-proceeding");
			IScriptStats stats=(IScriptStats)bundleContext.getService(ref);
			statScriptID=stats.beginScript(request, script, execThread,serverDomain);
		} else {
			System.err.println("Reference not found- stats disabled");
		}

	}

	private void afterService() {
		System.err.println("End of execution script "
				+ script.getClass().getCanonicalName());
		System.err.println("Script ID " + request.getUniqueId());
		System.err.println("Looking up for "+org.dmagiserver.stats.IScriptStats.class.getCanonicalName()+" service");
		ServiceReference ref= bundleContext.getServiceReference(org.dmagiserver.stats.IScriptStats.class.getCanonicalName());
		if (ref!=null) {
			System.err.println("Reference found-proceeding");
			IScriptStats stats=(IScriptStats)bundleContext.getService(ref);
			stats.endScript(request, script);
		} else {
			System.err.println("Reference not found- stats disabled");
		}

	}

	private void exceptionCathed(Throwable e) {
		System.err.println("Exception in thread "
				+ script.getClass().getCanonicalName());
		System.err.println("Script ID " + request.getUniqueId());
		System.err.println(e);
		e.printStackTrace();

	}

	class ScriptExecThread implements Runnable {
		@Override
		public void run() {
			try {
				script.service(request, channel);
			} catch (AgiException e) {
				exceptionCathed(e);
			}

		}
	}

}
