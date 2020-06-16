package spring.testing.server.bills;

import java.math.BigDecimal;

import spring.testing.server.exchange.Exchange;

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

	public Money add(Money m, Exchange exchange) {
		return new Money(amount.add(
				m.amount.multiply(exchange.getExchangeRate(m.currency, this.currency), MoneyConstants.ROUND_RULES)),
				this.currency);
	}

	public Money exchange(String currency, Exchange exchange) {
		return new Money(amount.multiply(exchange.getExchangeRate(this.currency, currency), MoneyConstants.ROUND_RULES),
				currency);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Money) {
			Money m = (Money) o;

			// Note: Two non zero money objects with different currencies
			// cannot be equal as exchange rates fluctuate.
			// Also this would make hashing impossible :-)
			return m.amount.equals(amount) && (m.amount.equals(BigDecimal.ZERO) || m.currency == currency);
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		return (amount.equals(BigDecimal.ZERO) ? 1 : currency.hashCode()) * amount.hashCode();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

}
