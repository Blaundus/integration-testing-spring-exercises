package spring.testing.server.unittests.bills;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.testing.server.bills.Money;
import spring.testing.server.exchange.ProductExchange;
import spring.testing.server.unittests.mocks.MockRateRepository;
public class MoneyTests {

  private ProductExchange exchange;
  private MockRateRepository mockRepository;

  @BeforeEach
  public void setUp() throws Exception {
	mockRepository = new MockRateRepository(); 
    exchange=new ProductExchange(mockRepository);
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
