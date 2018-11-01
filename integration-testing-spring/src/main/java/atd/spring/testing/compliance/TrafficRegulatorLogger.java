package atd.spring.testing.compliance;

import java.util.ArrayList;
import java.util.List;

import atd.spring.testing.bills.Bill;
import atd.spring.testing.bills.LineItem;

public class TrafficRegulatorLogger implements TrafficRegulator{
  private TrafficLog log;
  private boolean shouldLog=false;
  private boolean wasCreated=false;
  private List<LineItemTrafficRule> rules = new ArrayList<LineItemTrafficRule>();
  private Bill bill;
  
  public TrafficRegulatorLogger(List<LineItemTrafficRule> rules, TrafficLog logger) {
	    super();
	    this.rules = rules;
	    this.log = logger;
	  }

  public TrafficRegulatorLogger() {
    super();
	
    rules = new ArrayList<LineItemTrafficRule>();
	rules.add(new LogAmountOver(10));
	rules.add(new LogTotalAmountOver(20));

    this.log = new CheeseTrafficLog();
  }

  @Override
  public void registerBill(Bill bill) {
    this.bill = bill;
    if (shouldLog && !wasCreated) {
      log.log(bill);
      wasCreated=true;
    }
  }
  
  @Override
  public void apply() {
	  shouldLog = true;
  }

  @Override
  public void registerLineItem(LineItem item) {
    for(LineItemTrafficRule rule: rules) {
      if (shouldLog) break;
      if (rule.shouldLog(item)) {
        shouldLog=true;
        if (bill!=null) {
          log.log(bill);
          wasCreated=true;
        }
      }
    }
  }
  
  @Override
  public TrafficLog getLog() {
	  return log;
  }
}
