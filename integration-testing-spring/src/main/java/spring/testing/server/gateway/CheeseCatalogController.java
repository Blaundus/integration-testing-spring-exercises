package spring.testing.server.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.testing.server.entities.Cheese;
import spring.testing.server.exceptions.BannedException;
import spring.testing.server.exchange.Rate;
import spring.testing.server.persistence.jpa.CheeseRepository;

@Controller
public class CheeseCatalogController {

	@Autowired CheeseRepository cheeseRepository;
	
	@GetMapping(value ="products/all")
	public String getAllCheeses() {
		List<Cheese> products = cheeseRepository.findAll();
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

	public void addCheese(String name, String origin) throws BannedException {
		if (name.toLowerCase().contains("american") ||
				origin.toLowerCase().contains("usa")) {
			throw new BannedException();
		}
		else
			cheeseRepository.save(new Cheese(name, origin));
	}
}
