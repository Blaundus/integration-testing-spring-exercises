package atd.spring.server.compliance.logging;

import atd.spring.server.bills.Bill;

public interface BillLog {
  void log(Bill b);
  String getAllAsString();

}
