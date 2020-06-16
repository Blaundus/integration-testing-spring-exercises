package spring.testing.server.rules;

import spring.testing.server.bills.LineItem;

public interface CalculationRule {
  float getMultiplier(LineItem t);

}
