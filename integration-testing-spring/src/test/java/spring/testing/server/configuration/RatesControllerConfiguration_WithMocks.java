package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.testing.server.controllers.RatesController;



@Configuration
@Import({AppConfiguration.class})
public class RatesControllerConfiguration_WithMocks {
	
	@Bean
	public RatesController ratesController() {
		return new RatesController();
	}
	
}
