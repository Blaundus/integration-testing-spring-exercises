package spring.testing.server.helpers;

import spring.testing.server.exchange.Monitor;

public class FaultyMonitor extends Monitor{
	@Override
	public boolean isOk() {
		return false;
	}
	
	@Override
	public boolean isInitialized() {
		return false;
	}
	
}
