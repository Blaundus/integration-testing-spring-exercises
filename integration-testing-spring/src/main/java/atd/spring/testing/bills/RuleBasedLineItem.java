package atd.spring.testing.bills;

import java.math.BigDecimal;

import atd.spring.testing.rules.LineItemRule;

public class RuleBasedLineItem extends LineItem {
  LineItemRule rules; 

  public RuleBasedLineItem(String description, Money itemPrice, BigDecimal itemAmount, LineItemRule rules) {
    super(description, itemPrice, itemAmount);
    this.rules = rules;
  }
  
  @Override
  public Money getTotalAmount() {
    float price = getItemPrice().getAmount().floatValue();
    float amount = getItemAmount().floatValue();
    float factor = rules.getFactor(this);
    float ret = price*amount*factor;
    
    return new Money(BigDecimal.valueOf(ret),getItemPrice().getCurrency());
  }
  

}
