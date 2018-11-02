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
import atd.spring.server.compliance.TrafficRegulator;
import atd.spring.server.compliance.TrafficRegulatorLogger;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.exchange.Exchange;
import atd.spring.server.exchange.RateLoader;
import atd.spring.server.gateway.StatusMonitor;
import atd.spring.server.persistence.jdbc.RateRepository;
import atd.spring.server.rules.CompositeLineItemRule;
import atd.spring.server.rules.LineItemRule;

@Configuration
public class AppConfiguration {
	
	@Bean
	public RateRepository rateRepository() {
		return new RateRepository();
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
		return new CheeseExchange(rateRepository());
		
	}
	
	@Bean 
	public StatusMonitor statusMonitor() {
		return new StatusMonitor();
	}
	
	@Bean
	public TrafficRegulator trafficLogger( ) {
		return new TrafficRegulatorLogger();
	}
}
