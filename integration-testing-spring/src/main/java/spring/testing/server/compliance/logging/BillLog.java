package spring.testing.server.compliance.logging;

import spring.testing.server.bills.Bill;

public interface BillLog {
  void log(Bill b);
  String getAllAsString();

}
