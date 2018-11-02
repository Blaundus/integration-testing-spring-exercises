package atd.spring.testing.compliance;

import atd.spring.server.bills.Bill;

public interface TrafficLog {
  void log(Bill b);
  String getAll();

}
