package spring.testing.server.exchange;

import java.util.ArrayList;
import java.util.List;

public class Rates {
	List<String> rates = new ArrayList();
	
	public Rates() {
	}
	
	public Rates(List<String> newRates) {
		this.rates = newRates;
	}
	
	public void add(String rate) {
		rates.add(removeQuotes(rate));
	}

	private String removeQuotes(String rate) {
		return rate.substring(1, rate.length()-1);
	}

	public List<String> getRates() {
		return rates;
	}
	
	public void setRates(List<String> newRates) {
		this.rates = newRates;	
	}
}
