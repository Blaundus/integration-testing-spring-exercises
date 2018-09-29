package atd.spring.testing.unittests.bills;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import atd.spring.testing.bills.Money;
import atd.spring.testing.exchange.CentralExchange;

public class MoneyTest {

  private CentralExchange centralExchange;

  @Before
  public void setUp() throws Exception {
    centralExchange=new CentralExchange("ILS");
    centralExchange.setRate("JND", BigDecimal.valueOf(2));
  }

  @Test
  public void calculation_withBaseCurrency() {
    Money oneILS = new Money(BigDecimal.valueOf(1),"ILS");
    Money twoILS = new Money(BigDecimal.valueOf(2),"ILS");
    Money threeILS = new Money(BigDecimal.valueOf(3),"ILS");
    assertEquals(threeILS,oneILS.add(twoILS, centralExchange));
  }

  @Test
  public void calculation_withSameCurrency() {
    Money oneJND = new Money(BigDecimal.valueOf(1),"JND");
    Money twoJND = new Money(BigDecimal.valueOf(2),"JND");
    Money threeJND = new Money(BigDecimal.valueOf(3),"JND");
    assertEquals(threeJND,oneJND.add(twoJND, centralExchange));
  }
  
  @Test
  public void calculation_withDifferentCurrencies() {
    Money oneILS = new Money(BigDecimal.valueOf(1),"ILS");
    Money twoJND = new Money(BigDecimal.valueOf(2),"JND");
    Money fiveILS = new Money(BigDecimal.valueOf(5),"ILS");
    assertEquals(fiveILS,oneILS.add(twoJND, centralExchange));
  }
  
  @Test
  public void zeroMoneyInDifferentCurrencies_areEqual() {
    assertEquals(new Money("ILS"),new Money("JND"));
    assertEquals(new Money("ILS").hashCode(),new Money("JND").hashCode());
  }
  
}
