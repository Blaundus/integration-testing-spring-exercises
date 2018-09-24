package atd.spring.testing.bills;

import java.util.HashSet;
import java.util.Set;

import atd.spring.testing.exchange.ExchangeBureau;

public class Bill {
  Set<LineItem> items = new HashSet<>();
  
  public void addItem(LineItem item) {
    items.add(item);
  }
  
  public Money getTotal(ExchangeBureau exchange, String currency) {
    Money runningTotal = new Money(currency);
    for (LineItem item : items) {
      runningTotal = runningTotal.add(item.getTotalAmount(), exchange);
    }
    return runningTotal;
  }

}
