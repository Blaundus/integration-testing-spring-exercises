package atd.spring.server.unittests.mocks;

import java.math.BigDecimal;
import java.util.HashMap;

import atd.spring.server.exchange.Rate;
import atd.spring.testing.persistence.jdbc.RateRepository;

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
