package atd.spring.testing.compliance;

import atd.spring.testing.bills.Bill;

public class LogTrafficToScreen implements SnowTrafficLog {

  @Override
  public void log(Bill b) {
    System.out.println(b.hashCode());
  }
  

}
