package atd.spring.testing.compliance;

import atd.spring.testing.bills.LineItem;

public interface LineItemTrafficRule {
  boolean shouldLog(LineItem item);

}
