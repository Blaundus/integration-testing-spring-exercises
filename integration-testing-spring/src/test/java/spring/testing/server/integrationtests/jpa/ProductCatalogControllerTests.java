package spring.testing.server.integrationtests.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.ProductControllerConfiguration;
import spring.testing.server.controllers.ProductCatalogController;
import spring.testing.server.entities.Product;

@ContextConfiguration(classes= {ProductControllerConfiguration.class })
@DataJpaTest
public class ProductCatalogControllerTests{
	
	@Autowired ProductCatalogController controller;
	@Autowired TestEntityManager entityManager;

	@Test
	public void zeroRatesRetrieved_onCreation() {
		assertEquals("{}", controller.getAllProducts());
	}
		
	@Test
	public void twoRatesRetrieved_afterAddingTwo() {
		Product product1 = new Product("Brie", "France");
		Product product2 = new Product("Parmigiano", "Italy");
		
		entityManager.persist(product1);
		entityManager.persist(product2);
		entityManager.flush();
		
		
		assertEquals("{Brie from France,Parmigiano from Italy}", 
				controller.getAllProducts());
	}
	
	
}
