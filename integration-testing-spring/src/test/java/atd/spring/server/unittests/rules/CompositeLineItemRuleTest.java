package atd.spring.server.unittests.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.LineItem;
import atd.spring.server.rules.CompositeLineItemRule;
import atd.spring.server.rules.CalculationRule;

public class CompositeLineItemRuleTest {
  
  CompositeLineItemRule lineItemRule;
  
  @Before
  public void setUp() {
    List<CalculationRule> rules = new ArrayList<>();
    CalculationRule factorRule = new CalculationRule() {
      public float getMultiplier(LineItem t) {
        return 1.1f;
      }
    };
    rules.add(factorRule);
    rules.add(factorRule);
    
    lineItemRule = new CompositeLineItemRule(rules);
  }

  @Test
  public void calculation_multiplyByFactor() {
    assertEquals(1.21f,lineItemRule.getMultiplier(null),0.01);
  }

}
