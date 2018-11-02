package atd.spring.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import atd.spring.server.gateway.CheeseCatalogController;
import atd.spring.testing.configuration.JpaDataConfiguration;


@Configuration
@Import(JpaDataConfiguration.class)
@EnableJpaRepositories(basePackages = "atd.spring.testing")
public class CheeseControllerConfiguration {

	@Bean 
	CheeseCatalogController cheeseController() {
		return new CheeseCatalogController();
	}
}