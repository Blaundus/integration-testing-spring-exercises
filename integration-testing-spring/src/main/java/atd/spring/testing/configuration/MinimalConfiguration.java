package atd.spring.testing.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import atd.spring.testing.bills.LineItem;
import atd.spring.testing.exchange.CentralExchange;
import atd.spring.testing.exchange.Exchange;
import atd.spring.testing.exchange.RateLoader;
import atd.spring.testing.persistence.RateRepository;
import atd.spring.testing.rules.CompositeLineItemRule;
import atd.spring.testing.rules.LineItemRule;

@Configuration
public class MinimalConfiguration {
	
	@Bean
	public RateRepository rateRepository() {
		return new RateRepository();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource datasource ) {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.h2.Driver")
				.url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")
				.username("sa")
				.password("sa")
				.build();
	}
	

	@Bean
	public CompositeLineItemRule compositeLineItemRule() {
		List<LineItemRule> rules = new ArrayList<>();
	    LineItemRule factorRule = new LineItemRule() {
	      public float getFactor(LineItem t) {
	        return 1.1f;
	      }
	    };
	    rules.add(factorRule);
	    
		return new CompositeLineItemRule(rules);
	};

	@Bean 
	public RateLoader rateLoader() {
		return new RateLoader();
	}

	
	@Bean 
	public Exchange exchange() {
		return new CentralExchange(rateRepository());
	}
	

}
