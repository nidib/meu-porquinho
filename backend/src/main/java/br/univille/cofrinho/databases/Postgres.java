package br.univille.cofrinho.databases;

import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class Postgres {

	private static final Dotenv env = Dotenv
		.configure()
		.ignoreIfMissing()
		.load();

	private static final String SCHEMA = "main";

	private static final String URL = Optional
		.ofNullable(env.get("DB_URL"))
		.orElseThrow(() -> new RuntimeException("Missing DB_URL"));

	private static final String USER = Optional
		.ofNullable(env.get("DB_USER"))
		.orElseThrow(() -> new RuntimeException("Missing DB_USER"));

	private static final String PASSWORD = Optional
		.ofNullable(env.get("DB_PASSWORD"))
		.orElseThrow(() -> new RuntimeException("Missing DB_PASSWORD"));

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:" + URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWORD);

		return dataSource;
	}

	@Bean
	public Flyway flyway() {
		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:" + URL, USER, PASSWORD)
				.locations("classpath:db/migration")
				.defaultSchema(SCHEMA)
				.load();

		flyway.migrate();

		return flyway;
	}

}