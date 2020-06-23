package spring.testing.server.exchange;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

public class Rate {
	private String currency;
	private BigDecimal rateValue;
	
	// For data injection only
	public Rate() {}
	
	public Rate(String currency, BigDecimal value) {
		this.currency = currency;
		this.rateValue = value;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public BigDecimal getRateValue() {
		return this.rateValue;
	}
	
	public void setRateValue(BigDecimal rateValue) {
		this.rateValue = rateValue;
	}
	
	@Override
	public String toString() {
		String value = NumberFormat.getCurrencyInstance()
				.format(this.rateValue.setScale(2));
		return String.format("%s = %f", 
				this.currency, this.rateValue);
	}
	
	public BigDecimal divide(Rate toRate, MathContext roundRules) {
		return this.rateValue.divide(toRate.rateValue, roundRules);
	}
	
}
