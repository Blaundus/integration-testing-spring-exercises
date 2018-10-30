package atd.spring.testing.exchange;

import java.math.BigDecimal;

public interface Exchange {
  public void setRate(String currency,BigDecimal rate);
  public BigDecimal getExchangeRate(String from,String to);
}
