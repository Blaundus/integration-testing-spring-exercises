package atd.spring.server.integrationtests.exercises.e1;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import atd.spring.server.exchange.Exchange;
import atd.spring.server.exchange.RateParser;

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
