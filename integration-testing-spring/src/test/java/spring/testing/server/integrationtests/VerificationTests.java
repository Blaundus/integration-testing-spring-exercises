package spring.testing.server.integrationtests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.server.configuration.AppConfiguration;
import spring.testing.server.configuration.JdbcDataConfiguration;
import spring.testing.server.configuration.JpaDataConfiguration;
import spring.testing.server.persistence.jdbc.RateRepository;
import spring.testing.server.persistence.jpa.CheeseRepository;


@SpringBootTest
@ContextConfiguration(classes= {
		AppConfiguration.class, 
		JdbcDataConfiguration.class,
		JpaDataConfiguration.class})
public class VerificationTests {

	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired RateRepository rates;
	@Autowired CheeseRepository cheeses;
	
	@Test
	public void canRunIntegrationTests() {
		assertNotNull(rates); 
		assertNotNull(jdbcTemplate);
		assertNotNull(cheeses);
	}

}
