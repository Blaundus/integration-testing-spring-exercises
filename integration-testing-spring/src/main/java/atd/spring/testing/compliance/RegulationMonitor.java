package atd.spring.testing.compliance;

public class RegulationMonitor {
	
	private boolean shouldLog;
	private boolean shouldMonitor;
	private static RegulationMonitor theRegulator;
	
	public static RegulationMonitor getRegulator() {
		if (theRegulator == null) {
			theRegulator = new RegulationMonitor(true,true);
		}
		return theRegulator;
	}

	
	public RegulationMonitor(boolean shouldLog, boolean shouldMonitor) {
		this.shouldLog = shouldLog;
		this.shouldMonitor = shouldMonitor;
	}
	
	public void StartLog() {
		shouldLog = true;
	}
	
	public void StopLog() {
		shouldLog = false;
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


	public boolean shouldLog() {
		return shouldLog;
	}
}
