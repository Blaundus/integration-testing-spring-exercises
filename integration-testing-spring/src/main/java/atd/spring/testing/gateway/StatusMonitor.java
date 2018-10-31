package atd.spring.testing.gateway;

public class StatusMonitor {
	boolean isInitalized = false;
	
	
	public boolean isOk() {
		return isInitalized;
	}
	
	public void start() {
		isInitalized = true;
	}
	
	public void shutdown() {
		isInitalized = false;
	}
}
