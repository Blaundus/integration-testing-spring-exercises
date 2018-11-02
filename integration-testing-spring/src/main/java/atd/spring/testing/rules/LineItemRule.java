package atd.spring.testing.rules;

import atd.spring.server.bills.LineItem;

public interface LineItemRule {
  float getFactor(LineItem t);

}
