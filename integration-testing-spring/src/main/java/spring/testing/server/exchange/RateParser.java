package spring.testing.server.exchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import spring.testing.server.exceptions.ParsingException;


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
		Matcher m = getMatch(rateLine);
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
	
	// Exercise 1
	// Add multi pattern support
	List<Pattern> patterns = new ArrayList<Pattern> ();
	public void addLineTemplate(String template) {
		if (patterns.isEmpty()) {
			patterns.add(LINE_PATTERN);
		}
		patterns.add(Pattern.compile(template));
	}
	
	private Matcher getMatch(String rateLine) {
		Matcher result = LINE_PATTERN.matcher(rateLine);
		if (!patterns.isEmpty()) {
			for (Pattern pattern : patterns) {
				if (pattern.matcher(rateLine).matches())
					return pattern.matcher(rateLine);
			}
		}
		
		return result;
			
	}
}