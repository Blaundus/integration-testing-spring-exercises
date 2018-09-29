package atd.spring.testing.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import atd.spring.testing.bills.LineItem;
import atd.spring.testing.exchange.CentralExchange;
import atd.spring.testing.exchange.RateTextFileLoader;
import atd.spring.testing.exchange.SettableExchange;
import atd.spring.testing.rules.CompositeLineItemRule;
import atd.spring.testing.rules.LineItemRule;

@Configuration
public class ControllerConfiguration {
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
	public RateTextFileLoader rateTextFileLoader() {
		return new RateTextFileLoader();
	}
	
	@Bean 
	public SettableExchange settableExchange() {
		return new CentralExchange(null);
	}

}
