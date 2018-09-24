package atd.spring.testing.exchange;

import java.math.BigDecimal;

public interface SettableExchange extends ExchangeBureau {
  public void setRate(String currency,BigDecimal rate);

}
