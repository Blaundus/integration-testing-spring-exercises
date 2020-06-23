package spring.testing.server.integrationtests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.AppConfiguration;
import spring.testing.server.configuration.JDBC_DataConfiguration;
import spring.testing.server.configuration.JPA_DataConfiguration;
import spring.testing.server.persistence.jdbc.RateRepository;
import spring.testing.server.persistence.jpa.ProductRepository;

@SpringBootTest
@ContextConfiguration(classes= {
		AppConfiguration.class, 
		JDBC_DataConfiguration.class,
		JPA_DataConfiguration.class})
public class VerificationTests {

	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired RateRepository rates;
	@Autowired ProductRepository products;
	
	@Test
	public void canRunIntegrationTests() {
		assertNotNull(rates); 
		assertNotNull(jdbcTemplate);
		assertNotNull(products);
	}

}
