package atd.spring.server.unittests.exchange;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.MoneyConstants;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.unittests.mocks.MockRateRepository;

public class CheeseExchangeTests {
  
  private CheeseExchange exchange;
  private MockRateRepository mockRepository;

  @Before
  public void setUp() {
	mockRepository = new MockRateRepository();
    exchange=new CheeseExchange(mockRepository);
    exchange.setBaseRate("ILS");
    exchange.setRate("USD", BigDecimal.valueOf(3.8));
    exchange.setRate("CHF", BigDecimal.valueOf(7.6));
  }

  @Test
  public void sameCurrency_hasRateOfOne() {
    assertEquals(BigDecimal.valueOf(1),
                 exchange.getExchangeRate("ILS", "ILS"));
    assertEquals(BigDecimal.valueOf(1),
                 exchange.getExchangeRate("USD", "USD"));
  }
  
  @Test
  public void conversionToBaseCurrency_MultiplyByRate() {
    assertEquals(BigDecimal.valueOf(3.8), 
                 exchange.getExchangeRate("USD", "ILS"));
  }

  @Test
  public void conversionFromBaseCurrency_DivideByRate() {
    assertEquals(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3.8),MoneyConstants.ROUND_RULES),
                 exchange.getExchangeRate("ILS", "USD"));
  }

  @Test
  public void conversion_ToHigherValueCurrency() {
    assertEquals(BigDecimal.valueOf(0.5),
                 exchange.getExchangeRate("USD", "CHF"));
  }

  @Test
  public void conversion_ToLowerValueCurrency() {
    assertEquals(BigDecimal.valueOf(2),
                 exchange.getExchangeRate("CHF", "USD"));
  }
}

