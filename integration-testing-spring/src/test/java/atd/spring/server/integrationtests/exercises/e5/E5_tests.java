package atd.spring.server.integrationtests.exercises.e5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.configuration.CheeseControllerConfiguration;
import atd.spring.server.entities.Cheese;
import atd.spring.server.exceptions.BannedException;
import atd.spring.server.gateway.CheeseCatalogController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {CheeseControllerConfiguration.class })
@DataJpaTest
public class E5_tests {

	@Autowired CheeseCatalogController controller;
	@Autowired TestEntityManager entityManager;

	@Test(expected=BannedException.class)
	public void addingAmericanCheese_throws() throws BannedException {
		controller.addCheese("American cheese", "USA" );
	}
	
	@Test(expected=BannedException.class)
	public void addingCheeseFromUSA_throws() throws BannedException {
		controller.addCheese("Fromage", "USA" );
	}
	
	@Test
	public void addindCheeseFromEurope_ok() throws BannedException {
		boolean exceptionThrown = false;
		try {
			controller.addCheese("gouda", "Italy");
		}
		catch (BannedException e) {
			exceptionThrown = true;
		}
		assertFalse(exceptionThrown);
		
	}
}
