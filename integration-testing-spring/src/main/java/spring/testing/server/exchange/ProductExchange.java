package spring.testing.server.exchange;

import java.math.BigDecimal;

import spring.testing.server.bills.MoneyConstants;
import spring.testing.server.exceptions.UnknownCurrencyException;
import spring.testing.server.persistence.jdbc.RateRepository;

public class ProductExchange implements Exchange {
	private Rate baseRate;
	private String baseCurrency;

	RateRepository rates;

	public ProductExchange(RateRepository rates)
	{
		this.rates = rates;
	}
	
	public void setBaseRate(String baseCurrency) {
		baseRate = new Rate(baseCurrency, BigDecimal.valueOf(1));
		rates.addRate(baseRate);
	}
	
	public void setRate(String currency, BigDecimal value) {
		if (currency.equalsIgnoreCase(baseCurrency) && value.longValue() != 1.0) {
			throw new IllegalArgumentException("Base currency rate cannot differ from 1.0");
		}
		rates.addRate(new Rate(currency, value));
	}

	public BigDecimal getExchangeRate(String from, String to) {
		Rate fromRate = rates.findByCurrency(from);
		if (fromRate == null)
			throw new UnknownCurrencyException(from);
		Rate toRate = rates.findByCurrency(to);
		if (toRate == null)
			throw new UnknownCurrencyException(to);

		return fromRate.divide(toRate, MoneyConstants.ROUND_RULES);
	}

}
