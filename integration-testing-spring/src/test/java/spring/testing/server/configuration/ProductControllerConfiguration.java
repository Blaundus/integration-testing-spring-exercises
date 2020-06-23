package spring.testing.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.testing.server.controllers.ProductController;

@Configuration
public class ProductControllerConfiguration {

	@Bean
	ProductController productCatalogController() {
		return new ProductController();
	}

}
