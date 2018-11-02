package atd.spring.server.unittests.rules;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import atd.spring.testing.bills.LineItem;
import atd.spring.testing.bills.Money;
import atd.spring.testing.rules.FactorByCurrencyRule;

public class FactorByCurrencyRuleTest {
  private FactorByCurrencyRule lineItemRule;
  
  @Before
  public void setUp() {
    Map<String,Float> factors = new HashMap<>();
    factors.put("CHF",1.15f);
    factors.put("EUR",0.9f);
    
    lineItemRule = new FactorByCurrencyRule(factors);
    
  }

  @Test
  public void calculation_WithFactorRuleApplied() {
    assertEquals(1.15f,lineItemRule.getFactor(
    		new LineItem("item",new Money(BigDecimal.ZERO,"CHF")
    				,BigDecimal.ZERO)),0.01);
  }

  @Test
  public void calculation_WithoutFactorRuleApplied() {
    assertEquals(1f,lineItemRule.getFactor(
    		new LineItem("item",new Money(BigDecimal.ZERO,"JND")
    				,BigDecimal.ZERO)),0.01);
  }
}
