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



public class RateLoaderTest {
  private RateParser theUnit;
  @Mock private CheeseExchange exchangeMock;  
  
  @Before
  public void setUp() {
    theUnit = new RateParser();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIntegerRate() {
    theUnit.setExchangeRate("USD=2",exchangeMock);
    verify(exchangeMock).setRate("USD", BigDecimal.valueOf(2));
  }

  @Test
  public void testFractionRate() {
    theUnit.setExchangeRate("USD=0.5",exchangeMock);
    verify(exchangeMock).setRate("USD", BigDecimal.valueOf(0.5));
  }
  
  @Test
  public void testFloatRate() {
    theUnit.setExchangeRate("USD=2.5",exchangeMock);
    verify(exchangeMock).setRate("USD", BigDecimal.valueOf(2.5));
  }
  
  @Test(expected=ParsingException.class)
  public void testBadLinesIgnored() {
    theUnit.setExchangeRate("USDX=2.5",exchangeMock);
  }
  
}
