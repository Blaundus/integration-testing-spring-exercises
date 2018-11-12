package atd.spring.server.integrationtests.exercises.e4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import atd.spring.server.configuration.ExchangeControllerConfiguration;
import atd.spring.server.gateway.CheeseExchangeController;
import atd.spring.server.integrationtests.exercises.e1.E1_configuration;
import atd.spring.server.persistence.jdbc.RateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ExchangeControllerConfiguration.class })
@Sql(scripts = "classpath:CreateSchema.sql", 
executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class E4_tests {
	
	@Autowired CheeseExchangeController controller;
	
	@Test
	public void returnCurrencyWithoutEUR_whenRatesExist() {
		controller.addRate(asJsonString("ILS=2.5"));
		assertEquals("ILS,", controller.getCurrencies());
	}
	
	@Test
	public void returnError_whenNoRates() {
		assertEquals("No Rates Exist", controller.getCurrencies());
	}
	
	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
