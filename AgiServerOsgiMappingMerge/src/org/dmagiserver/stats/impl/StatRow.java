package org.dmagiserver.stats.impl;

import java.util.Date;

import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.dmagiserver.stats.IScriptStat;


class StatRow implements IScriptStat{
	AgiScript script;
	AgiRequest request;
	Thread thread;
	Date startDate;
	Date stopDate;
	String scriptID;
	Throwable unhandledException;
	String serverDomain;
	
	public StatRow() {
		// TODO Auto-generated constructor stub
	}
	public StatRow(AgiScript script, AgiRequest request,Thread thread) {
		this.request=request;
		this.thread=thread;
		this.script=script;
	}
	public void setScriptID(String scriptID) {
		this.scriptID = scriptID;
	}
	
	
	
	
	public AgiScript getScript() {
		return script;
	}
	public void setScript(AgiScript script) {
		this.script = script;
	}
	public AgiRequest getRequest() {
		return request;
	}
	public void setRequest(AgiRequest request) {
		this.request = request;
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public boolean isRunning() {
		if (stopDate==null)
			return true;
		if (startDate.after(stopDate))
			return true;
		
		return false;
	}
	@Override
	public String toString() {
		return "Script stat row "+super.toString();
	}
	@Override
	public Thread getExecutionThread() {
		return this.thread;
	}
	@Override
	public Class getHandlingClass() {
		return script.getClass();
	}
	@Override
	public String getScriptID() {
		return scriptID;
	}
	@Override
	public Throwable getUnhandledException() {
		return unhandledException;
	}
	@Override
	public String getServerDomain() {
		return serverDomain;
	}
	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}
	
}
