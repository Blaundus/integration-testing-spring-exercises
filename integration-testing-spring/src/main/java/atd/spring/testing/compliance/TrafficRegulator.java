package atd.spring.testing.compliance;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.LineItem;

public interface TrafficRegulator {
  void registerBill(Bill bill);
  void registerLineItem(LineItem item);
  void apply();
  TrafficLog getLog();
}
