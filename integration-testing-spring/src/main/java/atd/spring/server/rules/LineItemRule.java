package atd.spring.server.rules;

import atd.spring.server.bills.LineItem;

public interface LineItemRule {
  float getFactor(LineItem t);

}
