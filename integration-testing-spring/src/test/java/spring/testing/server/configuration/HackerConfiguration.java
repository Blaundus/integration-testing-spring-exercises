package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import spring.testing.server.controllers.RatesController;
import spring.testing.server.exchange.Monitor;
import spring.testing.server.helpers.FaultyMonitor;

@Profile("hacker")
@Configuration
public class HackerConfiguration {

	@Primary
	@Bean
	public Monitor faultyMonitor() {
		return new FaultyMonitor();
	}
	
	@Bean
	public RatesController exchangeController() {
		return new RatesController();
	}
	

}
