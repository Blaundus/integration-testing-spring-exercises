package atd.spring.testing.compliance;

import java.util.ArrayList;
import java.util.List;

import atd.spring.testing.bills.Bill;
import atd.spring.testing.bills.LineItem;

public class TrafficRegulatorImpl implements TrafficRegulator{
  private SnowTrafficLog logger;
  private boolean shouldLog=false;
  private boolean logMade=false;
  private List<LineItemTrafficRule> rules = new ArrayList<LineItemTrafficRule>();
  private Bill bill;
  
  public TrafficRegulatorImpl(List<LineItemTrafficRule> rules, SnowTrafficLog logger) {
    super();
    this.rules = rules;
    this.logger = logger;
  }

  @Override
  public void registerBill(Bill bill) {
    this.bill = bill;
    if (shouldLog && !logMade) {
      logger.log(bill);
      logMade=true;
    }
  }

  @Override
  public void registerLineItem(LineItem item) {
    for(LineItemTrafficRule rule: rules) {
      if (shouldLog) break;
      if (rule.shouldLog(item)) {
        shouldLog=true;
        if (bill!=null) {
          logger.log(bill);
          logMade=true;
        }
      }
    }
  }
}
