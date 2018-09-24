package atd.spring.testing.bills;

import java.math.BigDecimal;

import atd.spring.testing.exchange.ExchangeBureau;

/**
 * A single monetary amount in a given currency, e.g. $5
 * Immutable Value Object 
 * @author Omri
 *
 */
public class Money {
  private BigDecimal amount;
  private String currency;
  
  public Money(BigDecimal amount, String currency) {
    this.currency = currency;
    this.amount = amount;
  }
  
  public Money(String currency) {
    this.currency = currency;
    this.amount = BigDecimal.ZERO;
  }
  
  /**
   * Calculate result of adding two money amounts
   * @param m
   * @param exchange
   * @return
   */
  public Money add(Money m, ExchangeBureau exchange) {
    return new Money(
        amount.add(
            m.amount.multiply(
                exchange.getExchangeRate(m.currency, this.currency),
                MoneyConstants.ROUND_RULES)),
        this.currency);
  }
  
  /**
   * return the corollary in a different currency
   * @param currency
   * @param exchange
   * @return
   */
  public Money exchange(String currency,ExchangeBureau exchange) {
    return new Money(
        amount.multiply(
            exchange.getExchangeRate(this.currency, currency),
            MoneyConstants.ROUND_RULES)
        ,currency);
  }
  
  @Override
  public boolean equals(Object o) {
    if (o instanceof Money) {
      Money m=(Money)o;
      
      // Note: Two non zero money objects with different currencies
      // cannot be equal as exchange rates fluctuate.
      // Also this would make hashing impossible :-)
      return m.amount.equals(amount) && 
          (m.amount.equals(BigDecimal.ZERO) ||  m.currency==currency) ;
    } else {
      return false;
    }
    
  }
  
  @Override
  public int hashCode() {
    return (amount.equals(BigDecimal.ZERO)? 1 : currency.hashCode())*amount.hashCode();
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }
  
}
