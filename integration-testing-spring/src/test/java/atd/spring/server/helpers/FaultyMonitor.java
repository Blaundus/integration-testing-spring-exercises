package atd.spring.server.helpers;

import atd.spring.server.exceptions.MonitorNotInitializedException;
import atd.spring.server.gateway.StatusMonitor;

public class FaultyMonitor extends StatusMonitor{
	@Override
	public boolean isOk() {
		return false;
	}
	
	@Override
	public boolean isInitialized() {
		return false;
	}
	
}
