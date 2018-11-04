package atd.spring.server.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import atd.spring.server.bills.LineItem;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.compliance.logging.TrafficRegistrar;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.exchange.Exchange;
import atd.spring.server.exchange.RateParser;
import atd.spring.server.gateway.ExchangeStatus;
import atd.spring.server.persistence.jdbc.RateRepository;
import atd.spring.server.rules.CompositeLineItemRule;
import atd.spring.server.rules.CalculationRule;

@Configuration
public class AppConfiguration {
	
	@Bean
	public RateRepository rateRepository() {
		return new RateRepository();
	}

	@Bean
	public CompositeLineItemRule compositeLineItemRule() {
		List<CalculationRule> rules = new ArrayList<>();
	    CalculationRule factorRule = new CalculationRule() {
	      public float getMultiplier(LineItem t) {
	        return 1.1f;
	      }
	    };
	    rules.add(factorRule);
	    
		return new CompositeLineItemRule(rules);
	};

	@Bean 
	public RateParser rateParser() {
		return new RateParser();
	}

	
	@Bean 
	public Exchange exchange() {
		return new CheeseExchange(rateRepository());
		
	}
	
	@Bean 
	public ExchangeStatus statusMonitor() {
		return new ExchangeStatus();
	}
	
	@Bean
	public Registrar trafficLogger( ) {
		return new TrafficRegistrar();
	}
}
