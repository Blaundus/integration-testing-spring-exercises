package atd.spring.testing.exchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import atd.spring.testing.bills.MoneyConstants;
import atd.spring.testing.exceptions.UnknownCurrencyException;

public class CentralExchange implements SettableExchange {
  private String baseCurrency;
  Map<String,BigDecimal> rates = new HashMap<>();
  
  public CentralExchange(String baseCurrency) {
    this.baseCurrency = baseCurrency;
    rates.put(baseCurrency, BigDecimal.valueOf(1));
  }
  
  @Override
  public void setRate(String currency,BigDecimal rate) {
    if (currency.equalsIgnoreCase(baseCurrency) && rate.longValue()!=1.0) {
      throw new IllegalArgumentException("Base currency rate cannot differ from 1.0");
    }
    rates.put(currency, rate);
  }
  
  
  @Override
  public BigDecimal getExchangeRate(String from, String to) {
    BigDecimal fromRate = rates.get(from);
    if (fromRate==null) throw new UnknownCurrencyException(from);
    BigDecimal toRate = rates.get(to);
    if (toRate==null) throw new UnknownCurrencyException(to);
    
    return fromRate.divide(toRate,MoneyConstants.ROUND_RULES);
  }

}
