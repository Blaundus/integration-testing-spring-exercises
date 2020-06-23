package spring.testing.server.bills;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spring.testing.server.exceptions.ParsingException;


public class BillParser {

	public Bill createFromLineItems(List<String> lineItems) {
		Bill bill = new Bill();
		lineItems.forEach(lineItem -> parseLineItem(lineItem, bill));
		return bill;
	}

	public void parseLineItem(String line, Bill bill) {
		Matcher m = getLinePattern().matcher(line);
		if (m.matches()) {
			String desc = m.group(getDescIndex());
			BigDecimal amount = new BigDecimal(m.group(getAmountIndex()));
			BigDecimal price = new BigDecimal(m.group(getPriceIndex()));
			String currency = m.group(getCurrencyIndex());
			bill.addItem(createRuleBasedLineItem(desc, amount, price, currency));
		} else {
			throw new ParsingException("Unclear line item:" + line);
		}
	}

	protected LineItem createRuleBasedLineItem(String desc, BigDecimal amount, BigDecimal price, String currency) {
		return new LineItem(desc, new Money(price, currency), amount);
	}
	
	

	private Pattern LINE_PATTERN = Pattern.compile("^(.*)::(\\d+\\.?\\d*)::(\\d+\\.?\\d*)::(...)$");

	protected Pattern getLinePattern() {
		return LINE_PATTERN;
	}

	private int getDescIndex() {
		return 1;
	}

	private int getAmountIndex() {
		return 2;
	}

	private int getPriceIndex() {
		return 3;
	}

	private int getCurrencyIndex() {
		return 4;
	}
	

}
