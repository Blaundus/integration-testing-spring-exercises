package spring.testing.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.testing.server.entities.Product;
import spring.testing.server.exceptions.BannedException;
import spring.testing.server.exchange.Rate;
import spring.testing.server.persistence.jpa.ProductRepository;

@Controller
public class ProductCatalogController {

	@Autowired ProductRepository productRepository;
	
	@GetMapping(value ="products/all")
	public String getAllProducts() {
		List<Product> products = productRepository.findAll();
		if (products.size() == 0)
			return ("{}");
		
		return createProductString(products);
	}

	@PostMapping(value ="products/add")
	public void addProduct(String name, String origin) throws BannedException {
		if (name.equals("") ||
				origin.equals("")) {
			throw new BannedException();
		}
		else
			productRepository.save(new Product(name, origin));
	}

	private String createProductString(List<Product> products) {
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
