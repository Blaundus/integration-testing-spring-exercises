package spring.testing.server.integrationtests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import spring.testing.server.configuration.JPA_ControllerConfiguration;
import spring.testing.server.configuration.ProductControllerConfiguration;
import spring.testing.server.controllers.ProductController;
import spring.testing.server.entities.Product;

@ContextConfiguration(classes = { ProductControllerConfiguration.class, JPA_ControllerConfiguration.class })
@SpringBootTest
@AutoConfigureMockMvc
public class JPA_ProductCatalogControllerTests {

	@Autowired
	ProductController controller;

	@Autowired
	MockMvc mockMvc;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void zeroRatesRetrieved_onCreation() throws Exception {
		assertEquals("No Products Found", getAllProducts());
	}

	@Test
	@Transactional
	public void twoRatesRetrieved_afterAddingTwo() throws Exception {
		Product product1 = new Product("Brie", "France");
		Product product2 = new Product("Parmigiano", "Italy");

		entityManager.persist(product1);
		entityManager.persist(product2);
		entityManager.flush();

		assertEquals("{Brie from France,Parmigiano from Italy}", getAllProducts());
	}

	private String getAllProducts() throws Exception {
		MvcResult result = mockMvc.perform(get("/products/all")).andExpect(status().isOk()).andReturn();

		return result.getResponse().getContentAsString();
	}

}
