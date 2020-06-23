package spring.testing.server.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.testing.server.bills.LineItem;
import spring.testing.server.compliance.logging.Registrar;
import spring.testing.server.compliance.logging.TrafficRegistrar;
import spring.testing.server.exchange.Exchange;
import spring.testing.server.exchange.Monitor;
import spring.testing.server.exchange.ProductExchange;
import spring.testing.server.exchange.RateParser;
import spring.testing.server.persistence.jdbc.RateRepository;
import spring.testing.server.rules.CalculationRule;
import spring.testing.server.rules.CompositeLineItemRule;

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
		return new ProductExchange(rateRepository());
		
	}
	
	@Bean 
	public Monitor monitor() {
		return new Monitor();
	}
	
	@Bean
	public Registrar trafficLogger( ) {
		return new TrafficRegistrar();
	}
}
