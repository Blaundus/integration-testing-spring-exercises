package spring.testing.server.persistence.jdbc;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.testing.server.exchange.Rate;

@Repository
public class RateRepository {

	@Autowired JdbcTemplate jdbcTemplate;
	 
	public Rate findByCurrency(String currency) {
		Rate rate = jdbcTemplate.queryForObject(
				"select * from rates where currency=?", 
				new Object[] {currency},
				new BeanPropertyRowMapper<Rate>(Rate.class)
				);
		return rate;
	}
	
	public void addRate(Rate rate) {
				String currency = rate.getCurrency();
				BigDecimal rateValue = rate.getRateValue();
				int r = jdbcTemplate.update(
				"INSERT INTO rates(currency, rateValue) VALUES(?,?)"
				, currency, rateValue);
	}
 

	// e4
	public List<String> getAllCurrenciesButBase() {
		List<String> currencies = jdbcTemplate.queryForList(
				"select currency from rates order by currency ASC", String.class);
		if (currencies.size() == 0)
			return null;
					
		int pos =-1;
		for (int i = 0; i<currencies.size(); i++) {
			if (currencies.get(i) == "EUR") {
				pos = i;
			}
		}
		if (pos != -1)
			currencies.remove(pos);
		
		return currencies;  
	}

	// e6
	public void deleteAllRates() {
		jdbcTemplate.execute("delete * from rates");
	}

}
