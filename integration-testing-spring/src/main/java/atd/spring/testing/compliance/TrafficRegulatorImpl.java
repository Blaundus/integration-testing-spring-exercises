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
  
  public TrafficRegulatorImpl() {
    super();
	
    rules = new ArrayList<LineItemTrafficRule>();
	rules.add(new LogAmountOver(10));
	rules.add(new LogTotalAmountOver(20));

    this.logger = new LogTrafficToScreen();
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
          logger.log(bill);
          logMade=true;
        }
      }
    }
  }
}
