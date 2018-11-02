package atd.spring.server.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import atd.spring.server.exchange.Cheese;
import atd.spring.server.exchange.Rate;
import atd.spring.server.persistence.jpa.CheeseRepository;

@Controller
public class CheeseCatalogController {

	@Autowired CheeseRepository cheeseRepository;
	
	@RequestMapping(method = RequestMethod.GET, value ="products/all")
	public String getAllCheeses() {
		List<Cheese> products = cheeseRepository.findAll();
		return formatAsString(products);
	}
	
	private String formatAsString(List<Cheese> products) {
		if (products.size() == 0)
			return ("{}");
		
		StringBuilder result = new StringBuilder("{");
		products.forEach(product -> 
		{
			result.append(product.toString());
			result.append(",");
		});
		result.deleteCharAt(result.length()-1);
		result.append("}");
		return result.toString();
	}
}
