package atd.spring.server.unittests.bills;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.Money;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.unittests.mocks.MockRateRepository;

public class MoneyTests {

  private CheeseExchange exchange;
  private MockRateRepository mockRepository;

  @Before
  public void setUp() throws Exception {
	mockRepository = new MockRateRepository(); 
    exchange=new CheeseExchange(mockRepository);
    exchange.setBaseRate("ILS");
    exchange.setRate("JND", BigDecimal.valueOf(2));
  }

  @Test
  public void calculation_withBaseCurrency() {
    Money oneILS = new Money(BigDecimal.valueOf(1),"ILS");
    Money twoILS = new Money(BigDecimal.valueOf(2),"ILS");
    Money threeILS = new Money(BigDecimal.valueOf(3),"ILS");
    assertEquals(threeILS,oneILS.add(twoILS, exchange));
  }

  @Test
  public void calculation_withSameCurrency() {
    Money oneJND = new Money(BigDecimal.valueOf(1),"JND");
    Money twoJND = new Money(BigDecimal.valueOf(2),"JND");
    Money threeJND = new Money(BigDecimal.valueOf(3),"JND");
    assertEquals(threeJND,oneJND.add(twoJND, exchange));
  }
  
  @Test
  public void calculation_withDifferentCurrencies() {
    Money oneILS = new Money(BigDecimal.valueOf(1),"ILS");
    Money twoJND = new Money(BigDecimal.valueOf(2),"JND");
    Money fiveILS = new Money(BigDecimal.valueOf(5),"ILS");
    assertEquals(fiveILS,oneILS.add(twoJND, exchange));
  }
  
  @Test
  public void zeroMoneyInDifferentCurrencies_areEqual() {
    assertEquals(new Money("ILS"),new Money("JND"));
    assertEquals(new Money("ILS").hashCode(),new Money("JND").hashCode());
  }
  
}
