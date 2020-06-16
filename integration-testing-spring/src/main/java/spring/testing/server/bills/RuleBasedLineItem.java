package spring.testing.server.bills;

import java.math.BigDecimal;

import spring.testing.server.rules.CalculationRule;

public class RuleBasedLineItem extends LineItem {
  CalculationRule rules; 

  public RuleBasedLineItem(String description, Money itemPrice, BigDecimal itemAmount, CalculationRule rules) {
    super(description, itemPrice, itemAmount);
    this.rules = rules;
  }
  
  @Override
  public Money getTotalAmount() {
    float price = getItemPrice().getAmount().floatValue();
    float amount = getItemAmount().floatValue();
    float factor = rules.getMultiplier(this);
    float ret = price*amount*factor;
    
    return new Money(BigDecimal.valueOf(ret),getItemPrice().getCurrency());
  }
  

}
