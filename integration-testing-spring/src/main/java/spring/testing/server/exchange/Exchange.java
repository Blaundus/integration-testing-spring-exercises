package spring.testing.server.exchange;

import java.math.BigDecimal;

public interface Exchange {
  void setRate(String currency,BigDecimal rate);
  BigDecimal getExchangeRate(String from,String to);
  void setBaseRate(String baseCurrency);
}
