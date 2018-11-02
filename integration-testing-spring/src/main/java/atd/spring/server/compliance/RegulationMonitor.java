package atd.spring.server.compliance;

public class RegulationMonitor {
	
	private boolean shouldMonitor;
	private static RegulationMonitor theRegulator;
	
	public static RegulationMonitor getRegulator() {
		if (theRegulator == null) {
			theRegulator = new RegulationMonitor(true);
		}
		return theRegulator;
	}

	
	public RegulationMonitor(boolean shouldMonitor) {
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
