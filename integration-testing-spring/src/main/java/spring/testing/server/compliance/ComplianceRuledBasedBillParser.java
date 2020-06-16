package spring.testing.server.compliance;

import java.io.IOException;
import java.math.BigDecimal;

import spring.testing.server.bills.Bill;
import spring.testing.server.bills.LineItem;
import spring.testing.server.bills.RuleBasedBillParser;
import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.rules.CalculationRule;
import spring.testing.server.rules.CompositeLineItemRule;

public class ComplianceRuledBasedBillParser  extends RuleBasedBillParser{
  private Registrar registrar;
  
  public ComplianceRuledBasedBillParser(CompositeLineItemRule ruleManager, Registrar trafficRegulator) {
    super(ruleManager);
    this.registrar = trafficRegulator;
  }
  


	@Override
  protected LineItem createRuleBasedLineItem(String desc, BigDecimal amount, 
      BigDecimal price, String currency) {
    LineItem ret = super.createRuleBasedLineItem(desc, amount, price, currency);
    registrar.documentLineItem(ret);
    return ret;
  }
  
}
