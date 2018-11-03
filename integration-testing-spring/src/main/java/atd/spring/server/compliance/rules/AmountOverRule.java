package atd.spring.server.compliance.rules;

import atd.spring.server.bills.LineItem;

public class AmountOverRule implements LoggableRule{

  private double threshold;

  public AmountOverRule(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean shouldLog(LineItem item) {
    return (item.getItemAmount().doubleValue()>=threshold);
  }

}
