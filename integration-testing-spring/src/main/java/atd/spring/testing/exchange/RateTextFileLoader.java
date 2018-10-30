package atd.spring.testing.exchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import atd.spring.testing.exceptions.ParsingException;

// TODO: Rename class
 
public class RateTextFileLoader {
  private static Pattern LINE_PATTERN=Pattern.compile("^(...)=(\\d+\\.?\\d*)$");

  @Autowired 
  public Exchange exchange;

  public void add(List<String> rates)  {
	  rates.forEach(rate ->
	  	exctractRateToExchange(rate));
  }
  
  public void loadFromFile(String filename,CentralExchange exchange) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filename));
    try {
      String line;
      while ((line = br.readLine()) !=null) {
        exctractRateToExchange(line,exchange);
      } 
    }finally {
      br.close();
    }    
  }
  
  // TODO: Clean Up overloads
  
  /**
   * extracts data from a line an pushes to exchange
   * You can configure behavior by over-riding getLinePattern,
   * getCurrencyGroup and getRateGroup
   * @param line
   * @param exchange
   */
  public void exctractRateToExchange(String line, Exchange exchange) {
    Matcher m = getLinePattern().matcher(line);
    if (m.matches()) {
      BigDecimal rate = new BigDecimal(m.group(getRateGroup()));
      exchange.setRate(m.group(getCurrencyGroup()),rate);
    } else {
      throw new ParsingException("Unclear rate:"+line);
    }
  }
  
  public void exctractRateToExchange(String line) {
	  exctractRateToExchange(line, exchange);
  }
  
  /**
   * overriding this requires overriding getCurrencyGroup & getRateGroup
   * to match.
   * @return pattern used to parse rate lines
   */
  public Pattern getLinePattern() {
    return LINE_PATTERN;
  }
  
  /**
   * @return index in pattern of group that contains the currency symbol
   */
  protected int getCurrencyGroup() { return 1; }
  /**
   * @return index in pattern of group that contains the rate
   */
  protected int getRateGroup() { return 2; }
}
