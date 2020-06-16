package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.testing.server.configuration.JpaDataConfiguration;
import spring.testing.server.gateway.CheeseCatalogController;


@Configuration
@Import(JpaDataConfiguration.class)
public class CheeseControllerConfiguration {

	@Bean 
	CheeseCatalogController cheeseController() {
		return new CheeseCatalogController();
	}
}
