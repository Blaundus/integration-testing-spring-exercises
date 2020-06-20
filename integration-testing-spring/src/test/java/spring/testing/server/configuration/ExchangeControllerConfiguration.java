package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import spring.testing.server.configuration.AppConfiguration;
import spring.testing.server.configuration.JdbcDataConfiguration;
import spring.testing.server.controllers.ProductExchangeController;
import spring.testing.server.controllers.ExchangeStatus;
import spring.testing.server.helpers.LoggingMonitor;



@Configuration
@Import({AppConfiguration.class, JdbcDataConfiguration.class})
public class ExchangeControllerConfiguration {
	
	@Bean
	public ProductExchangeController exchangeController() {
		return new ProductExchangeController();
	}
	
	@Primary
	@Bean
	public ExchangeStatus loggingMonitor() {
		return new LoggingMonitor();
	}
	
}
