package spring.testing.server.bills;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import spring.testing.server.exchange.CheeseExchange;

public class Bill {
  Set<LineItem> items = new HashSet<>();

  @Autowired
  CheeseExchange exchange;
  
  public void addItem(LineItem item) {
    items.add(item);
  }
  
  //TODO: Replace with overload
  public Money getTotal(CheeseExchange exchange, String currency) {
    Money runningTotal = new Money(currency);
    for (LineItem item : items) {
      runningTotal = runningTotal.add(item.getTotalAmount(), exchange);
    }
    return runningTotal;
  }

  public Money getTotal(String currency) {
	  return getTotal(this.exchange, currency);
  }
  
  @Override 
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  items.forEach(item -> sb.append(item.toString()));
	  return sb.toString();
  }
  
}
