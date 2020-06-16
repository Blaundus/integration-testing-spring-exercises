package spring.testing.server.gateway;

public class ExchangeStatus {
	boolean isInitalized = false;
	boolean isOk = true;
	
	public boolean isOk() {
		return isOk;
	}
	
	public boolean isInitialized() {
		return isInitalized;
	}
	
	public void startMonitoring() {
		isInitalized = true;
	}
	
	public void stopMonitoring() {
		isInitalized = false;
	}
}
