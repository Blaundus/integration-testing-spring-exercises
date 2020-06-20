package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import spring.testing.server.controllers.ProductExchangeController;
import spring.testing.server.controllers.ExchangeStatus;
import spring.testing.server.helpers.FaultyStatus;

@Profile("hacker")
@Configuration
public class HackerConfiguration {

	@Primary
	@Bean
	public ExchangeStatus faultyStatus() {
		return new FaultyStatus();
	}
	
	@Bean
	public ProductExchangeController exchangeController() {
		return new ProductExchangeController();
	}
	

}
