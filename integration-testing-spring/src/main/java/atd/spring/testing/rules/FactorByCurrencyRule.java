package atd.spring.testing.rules;

import java.util.HashMap;
import java.util.Map;

import atd.spring.testing.bills.LineItem;

public class FactorByCurrencyRule implements LineItemRule {
  Map<String, Float> factors;
  
  public FactorByCurrencyRule(Map<String,Float> factors) {
    this.factors = new HashMap<String,Float>(factors);
  }

  public FactorByCurrencyRule() {
	// TODO: ATD: Fill rates from DB 
	//		Map<String, Float> factors = new HashMap<>();
	//		factors.put("CHF", 1.15f);
	//		factors.put("EUR", 0.9f);
  }
  
  
  @Override
  public float getFactor(LineItem t) {
    Float ret = factors.get(t.getItemPrice().getCurrency());
    return ret==null?1:ret.floatValue();
  }

}
