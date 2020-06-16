package spring.testing.server.compliance.logging;

import java.util.ArrayList;
import java.util.List;

import spring.testing.server.bills.Bill;
import spring.testing.server.bills.LineItem;
import spring.testing.server.compliance.rules.AmountOverRule;
import spring.testing.server.compliance.rules.LoggableRule;
import spring.testing.server.compliance.rules.TotalAmountOverRule;

public class TrafficRegistrar implements Registrar{
  private BillLog log;
  private boolean shouldLog=false;
  private boolean wasCreated=false;
  private List<LoggableRule> rules = new ArrayList<LoggableRule>();
  private Bill bill;
  
  public TrafficRegistrar(List<LoggableRule> rules, BillLog logger) {
	    super();
	    this.rules = rules;
	    this.log = logger;
	  }

  public TrafficRegistrar() {
    super();
	
    rules = new ArrayList<LoggableRule>();
	rules.add(new AmountOverRule(10));
	rules.add(new TotalAmountOverRule(20));

    this.log = new TrafficLog();
  }

  @Override
  public void documentBill(Bill bill) {
    this.bill = bill;
    if (shouldLog && !wasCreated) {
      log.log(bill);
      wasCreated=true;
    }
  }
  
  @Override
  public void start() {
	  shouldLog = true;
  }

  @Override
  public void documentLineItem(LineItem item) {
    for(LoggableRule rule: rules) {
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
  public BillLog getLog() {
	  return log;
  }

  	// e2
	@Override
	public boolean isTempered() {
		return false;
	}
}
