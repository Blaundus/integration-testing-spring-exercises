package atd.spring.server.compliance;

import atd.spring.server.bills.LineItem;

public interface LineItemTrafficRule {
  boolean shouldLog(LineItem item);

}
