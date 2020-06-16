package spring.testing.server.rules;

import spring.testing.server.bills.LineItem;

public class GratisOnStarRule implements CalculationRule {

  @Override
  public float getMultiplier(LineItem lineItem) {
    if (lineItem.getDescription()!=null && 
    		lineItem.getDescription().startsWith("*")) {
      return 0;
    } else {
      return 1;
    }
  }

}
