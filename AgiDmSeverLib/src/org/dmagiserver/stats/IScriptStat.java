package org.dmagiserver.stats;

import java.util.Date;

import org.asteriskjava.fastagi.AgiRequest;

public interface IScriptStat {
	public String getScriptID();
	public Class getHandlingClass();
	public Date getStartDate();
	public Date getStopDate();
	public Throwable getUnhandledException();
	public AgiRequest getRequest();
	public boolean isRunning();
	public Thread getExecutionThread();
	public String getServerDomain();
	
}
