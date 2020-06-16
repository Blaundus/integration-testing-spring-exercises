package spring.testing.server.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cheeses")
public class Cheese {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
 
	@Id
    private String name;
	 
    private String countryOfOrigin;
	
    public Cheese() {}
    
    public Cheese(String name, String country) {
    	this.name = name;
    	this.countryOfOrigin = country;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setCountry(String country) {
    	this.countryOfOrigin = country;
    }
    
    public String getCountry() {
    	return countryOfOrigin;
    }
    
	@Override
	public String toString() {
		return String.format("%s from %s", 
				this.getName(), this.countryOfOrigin);
	}

    
}
