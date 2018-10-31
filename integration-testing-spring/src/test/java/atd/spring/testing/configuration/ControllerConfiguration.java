package atd.spring.testing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import atd.spring.testing.gateway.ExchangeController;
import atd.spring.testing.gateway.StatusMonitor;
import atd.spring.testing.integrationtests.LoggingMonitor;

@Configuration
@Import(MinimalConfiguration.class)
public class ControllerConfiguration {
	
	@Bean
	public ExchangeController exchangeController() {
		return new ExchangeController();
	}
	
	@Primary
	@Bean
	public StatusMonitor loggingMonitor() {
		return new LoggingMonitor();
	}
}
