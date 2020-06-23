package spring.testing.server.unittests.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.testing.server.bills.LineItem;
import spring.testing.server.bills.Money;
import spring.testing.server.rules.CurrencyRule;
public class FactorByCurrencyRuleTests {
  private CurrencyRule lineItemRule;
  
  @BeforeEach
  public void setUp() {
    Map<String,Float> factors = new HashMap<>();
    factors.put("CHF",1.15f);
    factors.put("EUR",0.9f);
    
    lineItemRule = new CurrencyRule(factors);
    
  }

  @Test
  public void calculation_WithFactorRuleApplied() {
    assertEquals(1.15f,lineItemRule.getMultiplier(
    		new LineItem("item",new Money(BigDecimal.ZERO,"CHF")
    				,BigDecimal.ZERO)),0.01);
  }

  @Test
  public void calculation_WithoutFactorRuleApplied() {
    assertEquals(1f,lineItemRule.getMultiplier(
    		new LineItem("item",new Money(BigDecimal.ZERO,"JND")
    				,BigDecimal.ZERO)),0.01);
  }
}
