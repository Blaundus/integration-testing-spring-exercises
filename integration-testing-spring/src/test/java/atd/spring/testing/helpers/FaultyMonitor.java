package atd.spring.testing.helpers;

import atd.spring.testing.exceptions.MonitorNotInitializedException;
import atd.spring.testing.gateway.StatusMonitor;

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
