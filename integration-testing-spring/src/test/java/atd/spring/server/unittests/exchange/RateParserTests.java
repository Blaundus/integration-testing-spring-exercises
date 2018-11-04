package atd.spring.server.unittests.exchange;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import atd.spring.server.exceptions.ParsingException;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.exchange.RateParser;

public class RateParserTests {
  private RateParser parser;
  @Mock private CheeseExchange mockExchange;  
  
  @Before
  public void setUp() {
    parser = new RateParser();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIntegerRate() {
    parser.setExchangeRate("USD=2",mockExchange);
    verify(mockExchange).setRate("USD", BigDecimal.valueOf(2));
  }

  @Test
  public void testFractionRate() {
    parser.setExchangeRate("USD=0.5",mockExchange);
    verify(mockExchange).setRate("USD", BigDecimal.valueOf(0.5));
  }
  
  @Test
  public void testFloatRate() {
    parser.setExchangeRate("USD=2.5",mockExchange);
    verify(mockExchange).setRate("USD", BigDecimal.valueOf(2.5));
  }
  
  @Test(expected=ParsingException.class)
  public void testBadLinesIgnored() {
    parser.setExchangeRate("USDX=2.5",mockExchange);
  }
  
}
