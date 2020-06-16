package spring.testing.server.unittests.mocks;

import java.util.HashMap;

import spring.testing.server.exchange.Rate;
import spring.testing.server.persistence.jdbc.RateRepository;

public class MockRateRepository extends RateRepository {
	HashMap<String,Rate> rates;
	
	public MockRateRepository() {
		rates = new HashMap<>();
	}
	
	@Override
	public void addRate(Rate rate) {
		rates.put(rate.getCurrency(), rate);
	}
	
	@Override
	public Rate findByCurrency(String currency) {
		return rates.get(currency);
	}
	

}
