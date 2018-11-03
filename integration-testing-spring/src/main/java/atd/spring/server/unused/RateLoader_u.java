package atd.spring.server.unused;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import atd.spring.server.exchange.CheeseExchange;
import atd.spring.server.exchange.RateParser;

public class RateLoader_u {

	public void loadFromFile(RateParser rateLoader, String filename, CheeseExchange exchange) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				rateLoader.setExchangeRate(line, exchange);
			}
		} finally {
			br.close();
		}
	}

}
