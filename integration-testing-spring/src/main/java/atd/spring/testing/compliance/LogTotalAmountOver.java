package atd.spring.testing.compliance;

import atd.spring.server.bills.LineItem;

public class LogTotalAmountOver implements LineItemTrafficRule{

  private double threshold;
  private double totalAmount=0;

  public LogTotalAmountOver(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean shouldLog(LineItem item) {
    totalAmount+=item.getItemAmount().doubleValue();
    return (totalAmount>=threshold);
  }

}
