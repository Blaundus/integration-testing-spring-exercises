package atd.spring.server.rules;

import atd.spring.server.bills.LineItem;

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
