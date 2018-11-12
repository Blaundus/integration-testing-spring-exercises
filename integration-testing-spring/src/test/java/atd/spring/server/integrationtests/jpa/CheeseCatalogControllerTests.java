package atd.spring.server.integrationtests.jpa;

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

import atd.spring.server.configuration.CheeseControllerConfiguration;
import atd.spring.server.configuration.ExchangeControllerConfiguration;
import atd.spring.server.entities.Cheese;
import atd.spring.server.exchange.Rate;
import atd.spring.server.gateway.CheeseCatalogController;
import atd.spring.server.gateway.CheeseExchangeController;

@RunWith(SpringRunner.class)
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
