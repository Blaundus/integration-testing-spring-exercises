package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.testing.server.controllers.ComplianceController;

@Configuration
public class ComplianceControllerConfiguration {

	@Bean
	public ComplianceController regulationController() {
		return new ComplianceController();
	}
	
}
