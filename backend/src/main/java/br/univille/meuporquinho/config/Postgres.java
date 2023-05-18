package br.univille.meuporquinho.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Postgres {

	private final String url;

	private final String user;

	private final String senha;

	private final String schema;

	@Autowired
	public Postgres(VariaveisDeAmbiente variaveisDeAmbiente) {
		this.url = variaveisDeAmbiente.getBancoUrl();
		this.user = variaveisDeAmbiente.getBancoUser();
		this.senha = variaveisDeAmbiente.getBancoSenha();
		this.schema = "main";
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(this.getJdbcUrl());
		dataSource.setUsername(this.user);
		dataSource.setPassword(this.senha);

		return dataSource;
	}

	@Bean
	public Flyway flyway() {
		Flyway flyway = Flyway.configure()
				.dataSource(this.getJdbcUrl(), this.user, this.senha)
				.locations("classpath:db/migration")
				.defaultSchema(this.schema)
				.load();

		flyway.migrate();

		return flyway;
	}

	private String getJdbcUrl() {
		return "jdbc:" + this.url;
	}

}
