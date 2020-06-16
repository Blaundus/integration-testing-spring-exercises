package spring.testing.exercises.intro.e2;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.testing.server.exchange.Exchange;
import spring.testing.server.exchange.RateParser;

@Configuration
public class E2_configuration {

	@Bean
	public RateParser rateParser() {
		return new RateParser();
	}
	
}
