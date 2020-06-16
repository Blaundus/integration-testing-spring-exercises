package spring.testing.server.compliance.rules;

import spring.testing.server.bills.LineItem;

public class TotalAmountOverRule implements LoggableRule{

  private double threshold;
  private double totalAmount=0;

  public TotalAmountOverRule(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean shouldLog(LineItem item) {
    totalAmount+=item.getItemAmount().doubleValue();
    return (totalAmount>=threshold);
  }

}
