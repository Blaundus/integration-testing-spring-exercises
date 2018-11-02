package atd.spring.server.unittests.bills;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.LineItem;
import atd.spring.server.bills.Money;
import atd.spring.server.unittests.mocks.MockRateRepository;
import atd.spring.testing.exchange.CheeseExchange;

public class BillTest extends Bill {
  
  private CheeseExchange centralExchange;
  private LineItem israeliChair;
  private LineItem jordanianChair;
  private LineItem israeliTable;
  private MockRateRepository mockRepository;

  Money oneILS = new Money(BigDecimal.valueOf(1),"ILS");
  Money twoILS = new Money(BigDecimal.valueOf(2),"ILS");
  Money tenILS = new Money(BigDecimal.valueOf(10),"ILS");
  Money oneJND = new Money(BigDecimal.valueOf(1),"JND");
  Money fiveJND = new Money(BigDecimal.valueOf(5),"JND");
  Money threeJND = new Money(BigDecimal.valueOf(3),"JND");
  private LineItem sixJordanianChairs;
  

  
  @Before
  public void setUp() throws Exception {
	mockRepository = new MockRateRepository();
    centralExchange = new CheeseExchange(mockRepository);
    centralExchange.setBaseRate("ILS");
    centralExchange.setRate("JND", BigDecimal.valueOf(2));
    
    israeliChair = new LineItem("chair",twoILS,BigDecimal.ONE);
    jordanianChair = new LineItem("chair",oneJND,BigDecimal.ONE);
    sixJordanianChairs = new LineItem("chair",oneJND,BigDecimal.valueOf(6));
    israeliTable = new LineItem("table",tenILS,BigDecimal.ONE);
  }

  @Test
  public void emptyBill_hasZeroMoney() {
    Bill bill = new Bill();
    assertEquals(new Money("ILS"),bill.getTotal(centralExchange,"ILS"));
  }
  
  @Test
  public void singleLineBill_withoutCurrencyChange() {
    Bill bill = new Bill();
    bill.addItem(israeliChair);
    assertEquals(twoILS, bill.getTotal(centralExchange,"ILS"));
  }

  @Test
  public void singleLineBill_withCurrencyChange() {
    Bill bill = new Bill();
    bill.addItem(jordanianChair);
    assertEquals(twoILS, bill.getTotal(centralExchange,"ILS"));
  }
  
  @Test
  public void multiLineBill_withCurrencyChange() {
    Bill bill = new Bill();
    bill.addItem(sixJordanianChairs);
    bill.addItem(israeliTable);
    assertEquals(new Money(BigDecimal.valueOf(22),"ILS"), 
        bill.getTotal(centralExchange,"ILS"));
  }

}
