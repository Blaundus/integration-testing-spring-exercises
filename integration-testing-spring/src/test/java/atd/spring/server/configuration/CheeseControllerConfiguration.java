package atd.spring.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import atd.spring.server.gateway.CheeseCatalogController;


@Configuration
@Import(JpaDataConfiguration.class)
public class CheeseControllerConfiguration {

	@Bean 
	CheeseCatalogController cheeseController() {
		return new CheeseCatalogController();
	}
}
