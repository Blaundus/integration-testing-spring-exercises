package atd.spring.testing.integrationtests;

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

import atd.spring.testing.configuration.JdbcDataConfiguration;
import atd.spring.testing.configuration.AppConfiguration;
import atd.spring.testing.persistence.jdbc.RateRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {AppConfiguration.class, 
		JdbcDataConfiguration.class })
public class EmptyTest {

	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired RateRepository rates;
//	@Autowired RateJpaRepository jpaRates;
	
	@Test
	public void canRunIntegrationTests() {
		assertNotNull(rates); 
		assertNotNull(jdbcTemplate);
//		assertNotNull(jpaRates);
	}

}
