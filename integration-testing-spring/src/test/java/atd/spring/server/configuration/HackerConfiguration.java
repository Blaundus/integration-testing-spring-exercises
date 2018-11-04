package atd.spring.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import atd.spring.server.gateway.CheeseExchangeController;
import atd.spring.server.gateway.ExchangeStatus;
import atd.spring.server.helpers.FaultyStatus;

@Profile("hacker")
@Configuration
public class HackerConfiguration {

	@Primary
	@Bean
	public ExchangeStatus faultyStatus() {
		return new FaultyStatus();
	}
	
	@Bean
	public CheeseExchangeController exchangeController() {
		return new CheeseExchangeController();
	}
	

}
