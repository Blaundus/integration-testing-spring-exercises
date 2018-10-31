package atd.spring.testing.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import atd.spring.testing.persistence.jdbc.RateRepository;

@Configuration
public class JdbcDataConfiguration {
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource datasource ) {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.h2.Driver")
				.url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")
				.username("sa")
				.password("sa")
				.build();
	}
	
	@Bean
	public RateRepository rateRepository() {
		return new RateRepository();
	}
	
}
