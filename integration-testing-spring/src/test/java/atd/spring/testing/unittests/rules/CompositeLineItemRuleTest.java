package atd.spring.testing.unittests.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import atd.spring.testing.bills.LineItem;
import atd.spring.testing.rules.CompositeLineItemRule;
import atd.spring.testing.rules.LineItemRule;

public class CompositeLineItemRuleTest {
  
  CompositeLineItemRule theUnit;
  @Before
  public void setUp() {
    List<LineItemRule> rules = new ArrayList<>();
    LineItemRule a = new LineItemRule() {
      public float getFactor(LineItem t) {
        return 1.1f;
      }
    };
    rules.add(a);
    rules.add(a);
    
    theUnit = new CompositeLineItemRule(rules);
  }

  @Test
  public void test() {
    assertEquals(1.21f,theUnit.getFactor(null),0.01);
  }

}
