package spring.testing.server.helpers;

import spring.testing.server.controllers.ExchangeStatus;
import spring.testing.server.exceptions.MonitorNotInitializedException;

public class FaultyStatus extends ExchangeStatus{
	@Override
	public boolean isOk() {
		return false;
	}
	
	@Override
	public boolean isInitialized() {
		return false;
	}
	
}
