package atd.spring.server.helpers;

import atd.spring.server.gateway.ExchangeStatus;

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
