package atd.spring.testing.rules;

import java.util.ArrayList;
import java.util.List;

import atd.spring.testing.bills.LineItem;

public class CompositeLineItemRule implements LineItemRule {
  List<LineItemRule> rules;
  
  
  public CompositeLineItemRule(List<LineItemRule> rules) {
    this.rules = new ArrayList<LineItemRule>(rules);
  }
  
  public void addRules() {
	this.rules = new ArrayList<LineItemRule>();
	rules.add(new GratisOnStarRule());
	rules.add(new FactorByCurrencyRule());
  }

  @Override
  public float getFactor(LineItem t) {
    
    float factor = 1.0f;
    for (LineItemRule rule : rules) {
      factor*=rule.getFactor(t);
    }
    return factor;
  }

}
