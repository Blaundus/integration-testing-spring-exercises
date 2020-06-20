package spring.testing.server.unittests.bills;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.testing.server.bills.Bill;
import spring.testing.server.bills.LineItem;
import spring.testing.server.bills.Money;
import spring.testing.server.exchange.ProductExchange;
import spring.testing.server.unittests.mocks.MockRateRepository;

public class BillTests extends Bill {

	private ProductExchange exchange;
	private LineItem feta;
	private LineItem gouda;
	private LineItem parmagiane;
	private MockRateRepository mockRepository;

	Money oneILS = new Money(BigDecimal.valueOf(1), "ILS");
	Money twoILS = new Money(BigDecimal.valueOf(2), "ILS");
	Money tenILS = new Money(BigDecimal.valueOf(10), "ILS");
	Money oneJND = new Money(BigDecimal.valueOf(1), "JND");
	Money fiveJND = new Money(BigDecimal.valueOf(5), "JND");
	Money threeJND = new Money(BigDecimal.valueOf(3), "JND");
	private LineItem sixManchego;

	@BeforeEach
	public void setUp() throws Exception {
		mockRepository = new MockRateRepository();
		exchange = new ProductExchange(mockRepository);
		exchange.setBaseRate("ILS");
		exchange.setRate("JND", BigDecimal.valueOf(2));

		feta = new LineItem("feta", twoILS, BigDecimal.ONE);
		gouda = new LineItem("gouda", oneJND, BigDecimal.ONE);
		sixManchego = new LineItem("manchego", oneJND, BigDecimal.valueOf(6));
		parmagiane = new LineItem("parmagiane", tenILS, BigDecimal.ONE);
	}

	@Test
	public void emptyBill_hasZeroMoney() {
		Bill bill = new Bill();
		assertEquals(new Money("ILS"), bill.getTotal(exchange, "ILS"));
	}

	@Test
	public void singleLineBill_withoutCurrencyChange() {
		Bill bill = new Bill();
		bill.addItem(feta);
		assertEquals(twoILS, bill.getTotal(exchange, "ILS"));
	}

	@Test
	public void singleLineBill_withCurrencyChange() {
		Bill bill = new Bill();
		bill.addItem(gouda);
		assertEquals(twoILS, bill.getTotal(exchange, "ILS"));
	}

	@Test
	public void multiLineBill_withCurrencyChange() {
		Bill bill = new Bill();
		bill.addItem(sixManchego);
		bill.addItem(parmagiane);
		assertEquals(new Money(BigDecimal.valueOf(22), "ILS"), bill.getTotal(exchange, "ILS"));
	}

}
