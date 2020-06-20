package spring.testing.server.unittests.exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.testing.server.bills.MoneyConstants;
import spring.testing.server.exchange.ProductExchange;
import spring.testing.server.unittests.mocks.MockRateRepository;
public class ExchangeTests {
  
  private ProductExchange exchange;
  private MockRateRepository mockRepository;

  @BeforeEach
  public void setUp() {
	mockRepository = new MockRateRepository();
    exchange=new ProductExchange(mockRepository);
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

