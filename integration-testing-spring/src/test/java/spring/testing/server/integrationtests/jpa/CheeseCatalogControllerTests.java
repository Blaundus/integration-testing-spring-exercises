package spring.testing.server.integrationtests.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.CheeseControllerConfiguration;
import spring.testing.server.entities.Cheese;
import spring.testing.server.gateway.CheeseCatalogController;

@ContextConfiguration(classes= {CheeseControllerConfiguration.class })
@DataJpaTest
public class CheeseCatalogControllerTests{
	
	@Autowired CheeseCatalogController controller;
	@Autowired TestEntityManager entityManager;

	@Test
	public void zeroRatesRetrieved_onCreation() {
		assertEquals("{}", controller.getAllCheeses());
	}
		
	@Test
	public void twoRatesRetrieved_afterAddingTwo() {
		Cheese product1 = new Cheese("Brie", "France");
		Cheese product2 = new Cheese("Parmigiano", "Italy");
		
		entityManager.persist(product1);
		entityManager.persist(product2);
		entityManager.flush();
		
		
		assertEquals("{Brie from France,Parmigiano from Italy}", 
				controller.getAllCheeses());
	}
	
	
}
