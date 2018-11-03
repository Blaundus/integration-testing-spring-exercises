package atd.spring.server.exchange;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import atd.spring.server.exceptions.ParsingException;
import atd.spring.server.unused.RateLoader_u;

public class RateParser {
	
	
	private static Pattern LINE_PATTERN = Pattern.compile("^(...)=(\\d+\\.?\\d*)$");

	@Autowired public Exchange exchange;

	public void setBaseRate(String currency) {
		exchange.setBaseRate(currency);
	}

	public void parse(List<String> rates) {
		rates.forEach(rate -> setExchangeRate(rate));
	}

	public void setExchangeRate(String rateLine, Exchange exchange) {
		Matcher m = LINE_PATTERN.matcher(rateLine);
		if (m.matches()) {
			BigDecimal rate = new BigDecimal(m.group(getRateGroup()));
			exchange.setRate(m.group(getCurrencyGroup()), rate);
		} else {
			throw new ParsingException("Unclear rate:" + rateLine);
		}
	}

	public void setExchangeRate(String line) {
		setExchangeRate(line, exchange);
	}

	protected int getCurrencyGroup() {
		return 1;
	}

	protected int getRateGroup() {
		return 2;
	}

}
