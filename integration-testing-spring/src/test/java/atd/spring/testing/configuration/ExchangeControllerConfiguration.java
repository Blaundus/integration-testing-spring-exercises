package atd.spring.testing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import atd.spring.testing.gateway.CheeseExchangeController;
import atd.spring.testing.gateway.StatusMonitor;
import atd.spring.testing.helpers.LoggingMonitor;



@Configuration
@Import({AppConfiguration.class, JdbcDataConfiguration.class})
public class ExchangeControllerConfiguration {
	
	@Bean
	public CheeseExchangeController exchangeController() {
		return new CheeseExchangeController();
	}
	
	@Primary
	@Bean
	public StatusMonitor loggingMonitor() {
		return new LoggingMonitor();
	}
	
}
