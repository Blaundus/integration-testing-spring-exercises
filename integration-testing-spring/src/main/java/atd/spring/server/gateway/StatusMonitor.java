package atd.spring.server.gateway;

public class StatusMonitor {
	boolean isInitalized = false;
	boolean isOk = true;
	
	public boolean isOk() {
		return isOk;
	}
	
	public boolean isInitialized() {
		return isInitalized;
	}
	
	public void start() {
		isInitalized = true;
	}
	
	public void shutdown() {
		isInitalized = false;
	}
}
