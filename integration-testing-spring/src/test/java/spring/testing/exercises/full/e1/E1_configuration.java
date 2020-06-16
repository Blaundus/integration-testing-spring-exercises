package spring.testing.exercises.full.e1;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.testing.server.exchange.Exchange;
import spring.testing.server.exchange.RateParser;

@Configuration
public class E1_configuration {

	@Bean
	public RateParser rateParser() {
		return new RateParser();
	}
	
	@Bean 
	public Exchange mockExchange() {
		return Mockito.mock(Exchange.class);
	}
}
