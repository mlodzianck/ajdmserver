package org.dmagiserver.stats.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.dmagiserver.stats.IScriptStat;
import org.dmagiserver.stats.IScriptStats;

public class MemoryScriptStats implements IScriptStats {
	private HashMap<String, StatRow> repository = new HashMap<String, StatRow>();

	public MemoryScriptStats() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String beginScript(AgiRequest arg0, AgiScript arg1, Thread arg2,
			String serverDomain) {
		StatRow row = new StatRow();
		row.setRequest(arg0);
		row.setScript(arg1);
		row.setThread(arg2);
		row.setStartDate(new Date());
		row.setScriptID(request2ScriptID(arg0));
		row.setServerDomain(serverDomain);
		repository.put(request2ScriptID(arg0), row);
		System.err.println("beginScript- added stat row " + row + " with id "
				+ request2ScriptID(arg0)+" running on domain "+serverDomain);
		return request2ScriptID(arg0);

	}

	@Override
	public void endScript(AgiRequest arg0, AgiScript arg1) {
		if (repository.containsKey(request2ScriptID(arg0))) {
			repository.remove(request2ScriptID(arg0));
			System.err.println("endScript script with ID "
					+ request2ScriptID(arg0) + " removed");
		} else {
			System.err.println("endScript script with ID not found "
					+ request2ScriptID(arg0));
		}

	}

	@Override
	public int getAllScriptsCount() {
		return repository.keySet().size();
	}

	@Override
	public List<String> getRunningScriptsIDs() {
		return new LinkedList<String>(repository.keySet());
	}

	@Override
	public List<String> getScripID(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScriptStat getScriptStat(String arg0) {
		if (repository.containsKey(arg0)) {
			System.err.println("getScriptStat got script with ID " + arg0);
			return (IScriptStat) repository.get(arg0);
		} else {
			System.err.println("getScriptStat script with  ID " + arg0
					+ " not found");
			return null;
		}
	}

	private String request2ScriptID(AgiRequest request) {
		
		
		return request.getUniqueId() + "@"
				+ request.getRemoteAddress().toString();
	}

	@Override
	public List<String> getRunningScriptsIDsForDomain(String arg0) {
		System.out.println("MemoryScriptStats.getRunningScriptsIDsForDomain() for domain "+arg0);
		
		LinkedList<String> ret = new LinkedList<String>();

		Iterator<String> scriptIds = repository.keySet().iterator();
		while (scriptIds.hasNext()) {
			String scriptId = (String) scriptIds.next();
			if (repository.get(scriptId).getServerDomain().equals(arg0)) {
				ret.add(scriptId);
			}

		}
		return ret;
	}

}
