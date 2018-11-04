package atd.spring.server.integrationtests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.configuration.AppConfiguration;
import atd.spring.server.configuration.JdbcDataConfiguration;
import atd.spring.server.configuration.JpaDataConfiguration;
import atd.spring.server.persistence.jdbc.RateRepository;
import atd.spring.server.persistence.jpa.CheeseRepository;


@RunWith(SpringRunner.class)
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
