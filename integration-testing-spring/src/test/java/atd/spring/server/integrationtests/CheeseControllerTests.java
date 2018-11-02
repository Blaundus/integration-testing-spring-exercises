package atd.spring.server.integrationtests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.configuration.CheeseControllerConfiguration;
import atd.spring.testing.configuration.ExchangeControllerConfiguration;
import atd.spring.testing.exchange.Cheese;
import atd.spring.testing.exchange.Rate;
import atd.spring.testing.gateway.CheeseCatalogController;
import atd.spring.testing.gateway.CheeseExchangeController;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {CheeseControllerConfiguration.class })
@DataJpaTest
public class CheeseControllerTests {
	
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
