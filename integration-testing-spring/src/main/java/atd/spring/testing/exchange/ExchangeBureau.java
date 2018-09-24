package atd.spring.testing.exchange;

import java.math.BigDecimal;

public interface ExchangeBureau {
  BigDecimal getExchangeRate(String from,String to);
}
