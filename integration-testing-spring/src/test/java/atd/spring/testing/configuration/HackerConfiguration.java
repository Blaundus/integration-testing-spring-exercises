package atd.spring.testing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import atd.spring.testing.gateway.ExchangeController;
import atd.spring.testing.gateway.StatusMonitor;
import atd.spring.testing.helpers.FaultyMonitor;

@Profile("hacker")
@Configuration
public class HackerConfiguration {

	@Primary
	@Bean
	public StatusMonitor faultyMonitor() {
		System.out.println("Got a faulty one here");
		return new FaultyMonitor();
	}
	
	@Bean
	public ExchangeController exchangeController() {
		return new ExchangeController();
	}
	
}
