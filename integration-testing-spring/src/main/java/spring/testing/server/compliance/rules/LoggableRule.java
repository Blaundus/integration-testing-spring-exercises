package spring.testing.server.compliance.rules;

import spring.testing.server.bills.LineItem;

public interface LoggableRule {
  boolean shouldLog(LineItem item);
}
