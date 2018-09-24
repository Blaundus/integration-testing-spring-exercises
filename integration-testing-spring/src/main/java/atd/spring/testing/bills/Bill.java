package atd.spring.testing.bills;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import atd.spring.testing.exchange.ExchangeBureau;
import atd.spring.testing.exchange.SettableExchange;

public class Bill {
  Set<LineItem> items = new HashSet<>();

  @Autowired
  SettableExchange exchange;
  
  public void addItem(LineItem item) {
    items.add(item);
  }
  
  //TODO: Replace with overload
  public Money getTotal(ExchangeBureau exchange, String currency) {
    Money runningTotal = new Money(currency);
    for (LineItem item : items) {
      runningTotal = runningTotal.add(item.getTotalAmount(), exchange);
    }
    return runningTotal;
  }

  public Money getTotal(String currency) {
	  return getTotal(this.exchange, currency);
  }
}
