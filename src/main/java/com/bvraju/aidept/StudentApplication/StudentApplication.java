package com.bvraju.aidept.StudentApplication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main entry point for the Student Application.
 *
 * <p>
 * <strong>Transactional Management Notice:</strong>
 * <ul>
 * <li>This Spring Boot application uses <code>spring-jdbc</code> (not
 * <code>spring-boot-starter-jdbc</code>).</li>
 * <li>While <code>@EnableTransactionManagement</code> is included here,
 * it is <em>not required</em> in typical Spring Boot applications.</li>
 * <li>Spring Boot automatically enables annotation-driven transaction
 * management via <code>@EnableAutoConfiguration</code>,
 * as long as the following conditions are met:
 * <ul>
 * <li>A <code>DataSource</code> bean is available in the IoC container (either
 * auto-configured or user-defined)</li>
 * </ul>
 * </li>
 * <li>If these conditions are satisfied, the platform transaction manager
 * (e.g., <code>DataSourceTransactionManager</code>)
 * is auto-configured, and methods annotated with <code>@Transactional</code>
 * will work as expected.</li>
 * <li>Explicit use of <code>@EnableTransactionManagement</code> is mostly
 * needed for advanced or custom setups.</li>
 * </ul>
 * </p>
 */
@SpringBootApplication
@EnableTransactionManagement
public class StudentApplication {

	@Value("${mysql.database.url}")
	private String url;

	@Value("${mysql.database.username}")
	private String userName;

	@Value("${mysql.database.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	/*
	 * =============================================================================
	 * ============
	 * Manual JDBC Bean Configuration Notes
	 * =============================================================================
	 * ============
	 * This application intentionally uses `spring-jdbc` without the
	 * `spring-boot-starter-jdbc`.
	 *
	 * Key Implications of this Approach:
	 * 1. TransactionManager: Spring Boot's `@EnableAutoConfiguration`
	 * is smart
	 * a `PlatformTransactionManager` if
	 * it
	 * finds a JDBC driver on the classpath and `spring.datasource.*` properties.
	 *
	 * 2. DataSource & JdbcTemplate Not Auto-Configured: However, the
	 * auto-configuration for
	 * `JdbcTemplate`
	 * is part of a specific class (`JdbcTemplateAutoConfiguration`) that is only
	 * activated
	 * when `spring-boot-starter-jdbc` is used. Without the starter, this bean is
	 * NOT
	 * created automatically.
	 *
	 * 3. Recommendation: To ensure consistency and avoid ambiguity, it is a safer
	 * and clearer
	 * practice to manually define all core JDBC beans (`DataSource`,
	 * `JdbcTemplate`, and
	 * `PlatformTransactionManager`) when not using the starter. This makes the
	 * application's
	 * configuration explicit and predictable.
	 * =============================================================================
	 * ============
	 */

	/**
	 * Defines the primary {@link DataSource} bean for the application.
	 * <p>
	 * This bean is manually configured to connect to the database using credentials
	 * provided from application properties or other configuration sources. It
	 * serves
	 * as the fundamental connection factory for all JDBC operations.
	 * Make use of Actutator end points for bean in case of any further runtime
	 * troubleshooting
	 *
	 * @return A configured {@link DataSource} instance.
	 */
	@Bean
	public DataSource getDataSource() {
		// Note: It's recommended to use a connection pool DataSource like
		// HikariDataSource in production.
		// DriverManagerDataSource is suitable for simple applications and testing.
		DataSource ds = new DriverManagerDataSource(url, userName, password);
		return ds;
	}

	/**
	 * Defines the {@link JdbcTemplate} bean for executing JDBC operations.
	 * <p>
	 * This bean is explicitly created here because Spring Boot does not
	 * auto-configure it
	 * when `spring-boot-starter-jdbc` is omitted. It is wired with the manually
	 * configured {@code DataSource}.
	 * Make use of Actutator end points for bean in case of any further runtime
	 * troubleshooting
	 *
	 * @return A configured {@link JdbcTemplate} instance.
	 */
	@Bean
	public JdbcTemplate getJdbcTemplate(@Autowired DataSource datasource) {

		JdbcTemplate jdt = new JdbcTemplate(datasource);
		return jdt;
	}

	/**
	 * Defines the transaction manager bean for handling JDBC transactions.
	 * <p>
	 * While Spring Boot might auto-configure this bean, defining it manually
	 * alongside
	 * the {@code DataSource} and {@code JdbcTemplate} ensures a consistent and
	 * explicit
	 * configuration strategy. This bean enables the use of {@code @Transactional}
	 * annotations for methods interacting with the defined {@code DataSource}.
	 * Make use of Actutator end points for bean in case of any further runtime
	 * troubleshooting
	 *
	 * @return A configured {@link PlatformTransactionManager} instance.
	 */
	@Bean
	public PlatformTransactionManager getTransactionManager(@Autowired DataSource datasource) {
		PlatformTransactionManager tx = new DataSourceTransactionManager(datasource);
		return tx;
	}

}
