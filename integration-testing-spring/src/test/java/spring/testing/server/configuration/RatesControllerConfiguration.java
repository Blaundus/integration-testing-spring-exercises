package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import spring.testing.server.controllers.RatesController;
import spring.testing.server.exchange.Monitor;
import spring.testing.server.helpers.LoggingMonitor;



@Configuration
@Import({AppConfiguration.class, JDBC_DataConfiguration.class})
public class RatesControllerConfiguration {
	
	@Bean
	public RatesController ratesController() {
		return new RatesController();
	}
	
	@Primary
	@Bean
	public Monitor loggingMonitor() {
		return new LoggingMonitor();
	}
	
}
