package spring.testing.server.compliance.logging;

import spring.testing.server.bills.Bill;
import spring.testing.server.bills.LineItem;

public interface Registrar {
  void documentBill(Bill bill);
  void documentLineItem(LineItem item);
  void start();
  BillLog getLog();
  
  // e2
  boolean isTempered();
}
