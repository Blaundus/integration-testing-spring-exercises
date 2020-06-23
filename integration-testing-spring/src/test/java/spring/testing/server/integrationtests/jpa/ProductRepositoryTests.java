package spring.testing.server.integrationtests.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.JPA_DataConfiguration;
import spring.testing.server.configuration.ProductControllerConfiguration;
import spring.testing.server.controllers.ProductController;
import spring.testing.server.entities.Product;
import spring.testing.server.persistence.jpa.ProductRepository;

@ContextConfiguration(classes= {JPA_DataConfiguration.class })
@DataJpaTest
public class ProductRepositoryTests{
	@Autowired ProductRepository productRepository;
	@Autowired TestEntityManager entityManager;

	@Test
	public void zeroProductsRetrieved_onCreation() {
		assertEquals(0, productRepository.findAll().size());
	}
		
	@Test
	public void twoProductsRetrieved_afterAddingTwo() {
		Product product1 = new Product("Brie", "France");
		Product product2 = new Product("Parmigiano", "Italy");
		
		entityManager.persist(product1);
		entityManager.persist(product2);
		entityManager.flush();
		
		
		assertEquals(2, productRepository.findAll().size());
	}
	
	
}
