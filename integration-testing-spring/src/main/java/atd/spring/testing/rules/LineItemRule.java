package atd.spring.testing.rules;

import atd.spring.testing.bills.LineItem;

public interface LineItemRule {
  float getFactor(LineItem t);

}
