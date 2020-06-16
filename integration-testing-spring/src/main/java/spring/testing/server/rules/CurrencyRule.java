package spring.testing.server.rules;

import java.util.HashMap;
import java.util.Map;

import spring.testing.server.bills.LineItem;

public class CurrencyRule implements CalculationRule {
  Map<String, Float> currencies;
  
  public CurrencyRule(Map<String,Float> factors) {
    this.currencies = new HashMap<String,Float>(factors);
  }

  public CurrencyRule() {
			Map<String, Float> factors = new HashMap<>();
			factors.put("CHF", 1.15f);
			factors.put("EUR", 0.9f);
  }
  
  
  @Override
  public float getMultiplier(LineItem lineItem) {
    String currencyName = lineItem.getItemPrice().getCurrency();
	Float knownMultiplier = currencies.get(currencyName);
    return (knownMultiplier==null) ? 1 : knownMultiplier.floatValue();
  }

}
