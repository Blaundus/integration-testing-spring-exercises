package atd.spring.server.compliance.logging;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.LineItem;

public interface Registrar {
  void documentBill(Bill bill);
  void documentLineItem(LineItem item);
  void start();
  BillLog getLog();
  
  // e2
  boolean isTempered();
}
