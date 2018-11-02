package atd.spring.server.unittests.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.LineItem;
import atd.spring.testing.rules.CompositeLineItemRule;
import atd.spring.testing.rules.LineItemRule;

public class CompositeLineItemRuleTest {
  
  CompositeLineItemRule lineItemRule;
  
  @Before
  public void setUp() {
    List<LineItemRule> rules = new ArrayList<>();
    LineItemRule factorRule = new LineItemRule() {
      public float getFactor(LineItem t) {
        return 1.1f;
      }
    };
    rules.add(factorRule);
    rules.add(factorRule);
    
    lineItemRule = new CompositeLineItemRule(rules);
  }

  @Test
  public void calculation_multiplyByFactor() {
    assertEquals(1.21f,lineItemRule.getFactor(null),0.01);
  }

}
