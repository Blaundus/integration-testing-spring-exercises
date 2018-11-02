package atd.spring.server.rules;

import atd.spring.server.bills.LineItem;

public class GratisOnStarRule implements LineItemRule {

  @Override
  public float getFactor(LineItem t) {
    if (t.getDescription()!=null && t.getDescription().startsWith("*")) {
      return 0;
    } else {
      return 1;
    }
  }

}
