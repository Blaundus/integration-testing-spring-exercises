package atd.spring.testing.persistence.jdbc;

import java.io.Console;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;

import atd.spring.testing.exchange.Rate;

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

//	public List<Rate> getRates() {
//		return null;
//	}
}
