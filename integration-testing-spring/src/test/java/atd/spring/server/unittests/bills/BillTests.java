package atd.spring.server.unittests.bills;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.LineItem;
import atd.spring.server.bills.Money;
import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.unittests.mocks.MockRateRepository;

public class BillTests extends Bill {
  
  private CheeseExchange cheeseExchange;
  private LineItem feta;
  private LineItem gouda;
  private LineItem parmagiane;
  private MockRateRepository mockRepository;

  Money oneILS = new Money(BigDecimal.valueOf(1),"ILS");
  Money twoILS = new Money(BigDecimal.valueOf(2),"ILS");
  Money tenILS = new Money(BigDecimal.valueOf(10),"ILS");
  Money oneJND = new Money(BigDecimal.valueOf(1),"JND");
  Money fiveJND = new Money(BigDecimal.valueOf(5),"JND");
  Money threeJND = new Money(BigDecimal.valueOf(3),"JND");
  private LineItem sixManchego;
  

  
  @Before
  public void setUp() throws Exception {
	mockRepository = new MockRateRepository();
    cheeseExchange = new CheeseExchange(mockRepository);
    cheeseExchange.setBaseRate("ILS");
    cheeseExchange.setRate("JND", BigDecimal.valueOf(2));
    
    feta = new LineItem("feta",twoILS,BigDecimal.ONE);
    gouda = new LineItem("gouda",oneJND,BigDecimal.ONE);
    sixManchego = new LineItem("manchego",oneJND,BigDecimal.valueOf(6));
    parmagiane = new LineItem("parmagiane",tenILS,BigDecimal.ONE);
  }

  @Test
  public void emptyBill_hasZeroMoney() {
    Bill bill = new Bill();
    assertEquals(new Money("ILS"),bill.getTotal(cheeseExchange,"ILS"));
  }
  
  @Test
  public void singleLineBill_withoutCurrencyChange() {
    Bill bill = new Bill();
    bill.addItem(feta);
    assertEquals(twoILS, bill.getTotal(cheeseExchange,"ILS"));
  }

  @Test
  public void singleLineBill_withCurrencyChange() {
    Bill bill = new Bill();
    bill.addItem(gouda);
    assertEquals(twoILS, bill.getTotal(cheeseExchange,"ILS"));
  }
  
  @Test
  public void multiLineBill_withCurrencyChange() {
    Bill bill = new Bill();
    bill.addItem(sixManchego);
    bill.addItem(parmagiane);
    assertEquals(new Money(BigDecimal.valueOf(22),"ILS"), 
        bill.getTotal(cheeseExchange,"ILS"));
  }

}
