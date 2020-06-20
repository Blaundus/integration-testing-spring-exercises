package spring.testing.server.helpers;

import spring.testing.server.controllers.ExchangeStatus;

public class LoggingMonitor extends ExchangeStatus {

	
	@Override
	public void startMonitoring() {
		super.startMonitoring();
		System.out.println("Monitor has started");
	}
	
	@Override
	public void stopMonitoring() {
		super.stopMonitoring();
		System.out.println("Monitor has started");
	}
	
}
