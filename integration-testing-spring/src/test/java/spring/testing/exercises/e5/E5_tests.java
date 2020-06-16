package spring.testing.exercises.e5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.CheeseControllerConfiguration;
import spring.testing.server.exceptions.BannedException;
import spring.testing.server.gateway.CheeseCatalogController;

@ContextConfiguration(classes = { CheeseControllerConfiguration.class })
@DataJpaTest
public class E5_tests {

	@Autowired
	CheeseCatalogController controller;
	@Autowired
	TestEntityManager entityManager;

	@Test
	public void addingAmericanCheese_throws(){
		assertThrows(BannedException.class, () -> {
			controller.addCheese("American cheese", "USA");
		});
	}

	@Test
	public void addingCheeseFromUSA_throws() {
		assertThrows(BannedException.class, () -> {
			controller.addCheese("Fromage", "USA");
		});
	}

	@Test
	public void addindCheeseFromEurope_ok() throws BannedException {
		boolean exceptionThrown = false;
		try {
			controller.addCheese("gouda", "Italy");
		} catch (BannedException e) {
			exceptionThrown = true;
		}
		assertFalse(exceptionThrown);

	}
}
