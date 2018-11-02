package atd.spring.testing.compliance;

import atd.spring.server.bills.Bill;

public class CheeseTrafficLog implements TrafficLog {

	StringBuilder log = new StringBuilder();
  
	@Override
  public void log(Bill b) {
	log.append(">");	
    log.append(b.toString());
    log.append("\n");
  }

	@Override
	public String getAll() {
		return log.toString();
	}

}
