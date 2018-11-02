package atd.spring.server.unittests.exchange;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import atd.spring.testing.exceptions.ParsingException;
import atd.spring.testing.exchange.CheeseExchange;
import atd.spring.testing.exchange.RateLoader;



public class RateLoaderTest {
  private RateLoader theUnit;
  @Mock private CheeseExchange exchangeMock;  
  
  @Before
  public void setUp() {
    theUnit = new RateLoader();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIntegerRate() {
    theUnit.exctractRateToExchange("USD=2",exchangeMock);
    verify(exchangeMock).setRate("USD", BigDecimal.valueOf(2));
  }

  @Test
  public void testFractionRate() {
    theUnit.exctractRateToExchange("USD=0.5",exchangeMock);
    verify(exchangeMock).setRate("USD", BigDecimal.valueOf(0.5));
  }
  
  @Test
  public void testFloatRate() {
    theUnit.exctractRateToExchange("USD=2.5",exchangeMock);
    verify(exchangeMock).setRate("USD", BigDecimal.valueOf(2.5));
  }
  
  @Test(expected=ParsingException.class)
  public void testBadLinesIgnored() {
    theUnit.exctractRateToExchange("USDX=2.5",exchangeMock);
  }
  
}
