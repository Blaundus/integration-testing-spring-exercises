package atd.spring.server.rules;

import atd.spring.server.bills.LineItem;

public interface CalculationRule {
  float getMultiplier(LineItem t);

}
