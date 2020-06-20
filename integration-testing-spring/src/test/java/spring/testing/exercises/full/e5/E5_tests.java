package spring.testing.exercises.full.e5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.ProductControllerConfiguration;
import spring.testing.server.controllers.ProductCatalogController;
import spring.testing.server.exceptions.BannedException;

@ContextConfiguration(classes = { ProductControllerConfiguration.class })
@DataJpaTest
public class E5_tests {

	@Autowired
	ProductCatalogController controller;
	@Autowired
	TestEntityManager entityManager;

	@Test
	public void addingInvalidCountry_throws(){
		assertThrows(BannedException.class, () -> {
			controller.addProduct("Cheese", "");
		});
	}

	@Test
	public void addingInvalidProduct_throws() {
		assertThrows(BannedException.class, () -> {
			controller.addProduct("", "France");
		});
	}

	@Test
	public void addindValidCountryAndProduct_ok() throws BannedException {
		boolean exceptionThrown = false;
		try {
			controller.addProduct("Cheese", "France");
		} catch (BannedException e) {
			exceptionThrown = true;
		}
		assertFalse(exceptionThrown);

	}
}
