package atd.spring.testing.compliance;

import atd.spring.testing.bills.LineItem;

public class LogAmountOver implements LineItemTrafficRule{

  private double threshold;

  public LogAmountOver(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean shouldLog(LineItem item) {
    return (item.getItemAmount().doubleValue()>=threshold);
  }

}
