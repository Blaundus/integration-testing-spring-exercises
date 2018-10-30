package atd.spring.testing.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import atd.spring.testing.bills.LineItem;
import atd.spring.testing.exchange.CentralExchange;
import atd.spring.testing.exchange.RateTextFileLoader;

import atd.spring.testing.gateway.ExchangeController;
import atd.spring.testing.persistence.RateRepository;
import atd.spring.testing.rules.CompositeLineItemRule;
import atd.spring.testing.rules.LineItemRule;

@Configuration
//@Import(MinimalConfiguration.class)
public class ControllerConfiguration {
}
