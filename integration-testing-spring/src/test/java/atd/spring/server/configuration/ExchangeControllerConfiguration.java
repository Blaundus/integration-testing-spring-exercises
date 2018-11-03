package atd.spring.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import atd.spring.server.gateway.CheeseExchangeController;
import atd.spring.server.gateway.ExchangeStatus;
import atd.spring.server.helpers.LoggingMonitor;



@Configuration
@Import({AppConfiguration.class, JdbcDataConfiguration.class})
public class ExchangeControllerConfiguration {
	
	@Bean
	public CheeseExchangeController exchangeController() {
		return new CheeseExchangeController();
	}
	
	@Primary
	@Bean
	public ExchangeStatus loggingMonitor() {
		return new LoggingMonitor();
	}
	
}
