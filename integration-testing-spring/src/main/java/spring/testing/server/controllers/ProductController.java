package spring.testing.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import spring.testing.server.entities.Product;
import spring.testing.server.exceptions.BannedException;
import spring.testing.server.persistence.jpa.ProductRepository;

@Controller
public class ProductController {

	@Autowired ProductRepository productRepository;
	
	@GetMapping(value ="/products/all")
	public ResponseEntity<String> getAllProducts() {
		List<Product> products = productRepository.findAll();
		String result = "No Products Found";
		if (products.size() != 0)
			result = createProductString(products);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping(value ="/products/add")
	public ResponseEntity addProduct(String name, String origin) throws BannedException {
		if (name.equals("") ||
				origin.equals("")) {
			throw new BannedException();
		}
		else
			productRepository.save(new Product(name, origin));
		return new ResponseEntity(HttpStatus.OK);
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
