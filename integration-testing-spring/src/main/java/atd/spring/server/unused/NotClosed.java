package atd.spring.server.unused;

import java.math.BigDecimal;
import java.util.Map;

public class NotClosed {
  private Map<String,BigDecimal> rates;
  
  public NotClosed(Map<String,BigDecimal> rates) {
    this.rates = rates;
  }
  
  public BigDecimal getRate(String code) {
    return rates.get(code);
  }

}
