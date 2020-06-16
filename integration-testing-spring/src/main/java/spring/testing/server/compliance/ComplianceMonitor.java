package spring.testing.server.compliance;

public class ComplianceMonitor {
	
	private boolean shouldMonitor;
	private static ComplianceMonitor theMonitor;
	
	public static ComplianceMonitor instance() {
		if (theMonitor == null) {
			theMonitor = new ComplianceMonitor(true);
		}
		return theMonitor;
	}

	
	public ComplianceMonitor(boolean shouldMonitor) {
		this.shouldMonitor = shouldMonitor;
	}
	
	public void StartMonitoring() {
		shouldMonitor = true; 
	}
	
	public void StopMonitoring() {
		shouldMonitor = false;
	}


	public boolean shouldMonitor() {
		return shouldMonitor;
	}

}
