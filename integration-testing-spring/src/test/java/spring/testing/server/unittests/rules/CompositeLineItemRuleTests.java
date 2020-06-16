package spring.testing.server.unittests.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.testing.server.bills.LineItem;
import spring.testing.server.rules.CalculationRule;
import spring.testing.server.rules.CompositeLineItemRule;
public class CompositeLineItemRuleTests {
  
  CompositeLineItemRule lineItemRule;
  
  @BeforeEach
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
