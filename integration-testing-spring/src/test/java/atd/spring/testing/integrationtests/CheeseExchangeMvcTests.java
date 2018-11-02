package atd.spring.testing.integrationtests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import atd.spring.testing.configuration.ExchangeControllerConfiguration;
import atd.spring.testing.exchange.Rates;
import atd.spring.testing.gateway.CheeseExchangeController;
import atd.spring.testing.persistence.jdbc.RateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ExchangeControllerConfiguration.class})
@Sql(scripts = "classpath:CreateSchema.sql", 
	executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:DeleteSchema.sql", 
	executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CheeseExchangeMvcTests {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),                        
            Charset.forName("utf8")                     
            );
	
	@Autowired CheeseExchangeController controller;
	@Autowired RateRepository repository;
	@Autowired private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Test
	public void canRunControllerTests() {
		assertNotNull(controller);
		assertNotNull(repository);
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.build();
		controller.Reset();
	}
	
	@Test
	public void controllerBean_Loads() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    assertNotNull(servletContext);
	    assertTrue(servletContext instanceof MockServletContext);
	    assertNotNull(wac.getBean("exchangeController"));
	}
	
	@Test
	public void whenNoRates_ReturnAnErrorCode() throws Exception {
		this.mockMvc.perform(get("/rates/currency/?name=EUR"))
    	.andExpect(status().isNotFound());
	}
	
	@Test
	public void ratesAreAddedCorrectly() throws Exception {
		String rate = "ILS=2.5";
		String jsonRates = asJsonString(rate);

	    this.mockMvc.perform(
	    					post("/rates/add")
	    					.content(jsonRates)
	    					.contentType("application/json")
	    					.characterEncoding("UTF-8"))
	    		.andDo(print())
	    		.andExpect(status().isOk());
	}
	
	@Test
	@Ignore
	public void ratesAreAdded_withBaseRate() throws Exception {
		List<String> rates = List.of("ILS=2.5", "USD=3.8");
	    String jsonRates = asJsonString(rates);

	    this.mockMvc.perform(
	    					post("rest/rates/add")
	    					.param("rates", jsonRates)
	    					.contentType("application/json"))
	    		.andDo(print())
	    		.andExpect(status().isOk());
//	    
//	    this.mockMvc.perform(get("/rates/currency/?currency=EUR"))
//	    	.andExpect(status().isOk())
//	    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//	        .andExpect(jsonPath("$.id", is("EUR = 1.000000")));
////    
////		assertEquals("ILS = 2.500000", controller.getRateByCurrency("ILS"));
////		assertEquals("USD = 3.800000", controller.getRateByCurrency("USD"));
	}

	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	


}
