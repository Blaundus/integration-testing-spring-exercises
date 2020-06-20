package spring.testing.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import spring.testing.server.configuration.AppConfiguration;

@SpringBootApplication
@Import({AppConfiguration.class})
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
 	}

	private static void testMethod(String[] args) {
//		try {
//			// TODO: Clean Up here - remove
//			if (args.length != 3) {
//				help();
//			} else {
//				CentralExchange exchange = new CentralExchange("EUR");
//				RateTextFileLoader rateLoader = new RateTextFileLoader();
//				rateLoader.loadFromFile(args[0], exchange);
//
//				// V2: Add rules
//				Map<String, Float> factors = new HashMap<>();
//				factors.put("CHF", 1.15f);
//				factors.put("EUR", 0.9f);
//
//				List<LineItemRule> rules = new ArrayList<LineItemRule>();
//				rules.add(new GratisOnStarRule());
//				rules.add(new FactorByCurrencyRule(factors));
//				CompositeLineItemRule ruleManager = new CompositeLineItemRule(rules);
//
//				// V3: add compliance logging
//				SnowTrafficLog complianceLog = new LogTrafficToScreen();
//				List<LineItemTrafficRule> complianceRules = new ArrayList<LineItemTrafficRule>();
//				complianceRules.add(new LogAmountOver(10));
//				complianceRules.add(new LogTotalAmountOver(20));
////				TrafficRegulator trafficRegulator = new TrafficRegulatorImpl(complianceRules, complianceLog);
//
////				BillTextFileLoader billLoader = new CompliantRuledBillTextFileLoader(ruleManager, trafficRegulator);
////				Bill bill = billLoader.loadFromFile(args[1]);
////
////				Money result = bill.getTotal(exchange, args[2]);
////				System.out.println(result.getCurrency() + " " + result.getAmount());
//			}
//		} catch (Exception e) {
//			System.out.println("Error:" + e.getMessage());
//		}
	}

	public static void help() {
		System.out.println("To execute pass 3 params: rate-file, bill-file, currency");
	}
	

}
