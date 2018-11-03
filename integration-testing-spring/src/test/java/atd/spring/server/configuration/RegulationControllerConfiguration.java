package atd.spring.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import atd.spring.server.gateway.ComplianceController;

@Configuration
public class RegulationControllerConfiguration {

	@Bean
	public ComplianceController regulationController() {
		return new ComplianceController();
	}
	
}
