package atd.spring.testing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import atd.spring.testing.gateway.ExchangeController;

@Configuration
@Import(MinimalConfiguration.class)
public class ControllerConfiguration {
	
	@Bean
	public ExchangeController exchangeController() {
		return new ExchangeController();
	}
}
