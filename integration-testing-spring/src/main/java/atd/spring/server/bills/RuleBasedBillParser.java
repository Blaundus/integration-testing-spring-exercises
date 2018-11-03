package atd.spring.server.bills;

import java.math.BigDecimal;

import atd.spring.server.rules.LineItemRule;

public class RuleBasedBillParser extends BillParser {
  LineItemRule rules; 
  
  public RuleBasedBillParser (LineItemRule rules) {
    this.rules = rules;
  }
  
  protected LineItem createRuleBasedLineItem(String desc, BigDecimal amount, BigDecimal price,
      String currency) {
    return new RuleBasedLineItem(
      desc,
      new Money(price,currency),
      amount,
      rules);
  }

}
