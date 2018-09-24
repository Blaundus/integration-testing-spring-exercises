package atd.spring.testing.compliance;

import atd.spring.testing.bills.Bill;
import atd.spring.testing.bills.LineItem;

public interface TrafficRegulator {
  void registerBill(Bill bill);
  void registerLineItem(LineItem item);
}
