package spring.testing.server.rules;

import java.util.ArrayList;
import java.util.List;

import spring.testing.server.bills.LineItem;

public class CompositeLineItemRule implements CalculationRule {
  List<CalculationRule> rules;
  
  
  public CompositeLineItemRule(List<CalculationRule> rules) {
    this.rules = new ArrayList<CalculationRule>(rules);
  }
  
  public void addRules() {
	this.rules = new ArrayList<CalculationRule>();
	rules.add(new GratisOnStarRule());
	rules.add(new CurrencyRule());
  }

  @Override
  public float getMultiplier(LineItem t) {
    
    float factor = 1.0f;
    for (CalculationRule rule : rules) {
      factor*=rule.getMultiplier(t);
    }
    return factor;
  }

}
