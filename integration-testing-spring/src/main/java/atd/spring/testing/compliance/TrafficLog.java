package atd.spring.testing.compliance;

import atd.spring.testing.bills.Bill;

public interface TrafficLog {
  void log(Bill b);
  String getAll();

}
