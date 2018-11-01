package atd.spring.testing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import atd.spring.testing.gateway.RegulationController;

@Configuration
public class RegulationControllerConfiguration {

	@Bean
	public RegulationController regulationController() {
		return new RegulationController();
	}
	
}
