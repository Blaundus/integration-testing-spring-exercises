package atd.spring.server.helpers;

import atd.spring.server.exceptions.MonitorNotInitializedException;
import atd.spring.server.gateway.ExchangeStatus;

public class FaultyMonitor extends ExchangeStatus{
	@Override
	public boolean isOk() {
		return false;
	}
	
	@Override
	public boolean isInitialized() {
		return false;
	}
	
}
