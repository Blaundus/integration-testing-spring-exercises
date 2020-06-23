package spring.testing.exercises.full.e4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.testing.server.configuration.RatesControllerConfiguration;
import spring.testing.server.controllers.RatesController;
import spring.testing.server.helpers.JsonHelper;

@SpringBootTest
@ContextConfiguration(classes= {RatesControllerConfiguration.class })
@Sql(scripts = "classpath:CreateSchema.sql", 
executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class E4_tests {
	
	@Autowired RatesController controller;
	
	@Test
	public void returnCurrencyWithoutEUR_whenRatesExist() {
		controller.addRate(JsonHelper.asJsonString("ILS=2.5"));
		assertEquals("ILS,", controller.getCurrencies());
	}
	
	@Test
	public void returnError_whenNoRates() {
		assertEquals("No Rates Exist", controller.getCurrencies());
	}
	
}
