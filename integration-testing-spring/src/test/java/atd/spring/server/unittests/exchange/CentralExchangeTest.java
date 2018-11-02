package atd.spring.server.unittests.exchange;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.unittests.mocks.MockRateRepository;
import atd.spring.testing.bills.MoneyConstants;
import atd.spring.testing.exchange.CheeseExchange;

public class CentralExchangeTest {
  
  private CheeseExchange centralExchange;
  private MockRateRepository mockRepository;

  @Before
  public void setUp() {
	mockRepository = new MockRateRepository();
    centralExchange=new CheeseExchange(mockRepository);
    centralExchange.setBaseRate("ILS");
    centralExchange.setRate("USD", BigDecimal.valueOf(3.8));
    centralExchange.setRate("CHF", BigDecimal.valueOf(7.6));
  }

  @Test
  public void sameCurrency_hasRateOfOne() {
    assertEquals(BigDecimal.valueOf(1),
                 centralExchange.getExchangeRate("ILS", "ILS"));
    assertEquals(BigDecimal.valueOf(1),
                 centralExchange.getExchangeRate("USD", "USD"));
  }
  
  @Test
  public void conversionToBaseCurrency_MultiplyByRate() {
    assertEquals(BigDecimal.valueOf(3.8), 
                 centralExchange.getExchangeRate("USD", "ILS"));
  }

  @Test
  public void conversionFromBaseCurrency_DivideByRate() {
    assertEquals(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3.8),MoneyConstants.ROUND_RULES),
                 centralExchange.getExchangeRate("ILS", "USD"));
  }

  @Test
  public void conversion_ToHigherValueCurrency() {
    assertEquals(BigDecimal.valueOf(0.5),
                 centralExchange.getExchangeRate("USD", "CHF"));
  }

  @Test
  public void conversion_ToLowerValueCurrency() {
    assertEquals(BigDecimal.valueOf(2),
                 centralExchange.getExchangeRate("CHF", "USD"));
  }
}

