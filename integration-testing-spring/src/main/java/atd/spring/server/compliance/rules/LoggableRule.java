package atd.spring.server.compliance.rules;

import atd.spring.server.bills.LineItem;

public interface LoggableRule {
  boolean shouldLog(LineItem item);
}
