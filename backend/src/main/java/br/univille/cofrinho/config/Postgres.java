package br.univille.cofrinho.config;

import br.univille.cofrinho.exceptions.VariavelDeAmbienteNaoConfiguradaException;
import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class Postgres {

	private final String bancoUrl;

	private final String bancoUser;

	private final String bancoSenha;

	private final String bancoSchema;

	@Autowired
	public Postgres(VariaveisDeAmbiente variaveisDeAmbiente) {
		this.bancoUrl = variaveisDeAmbiente.getBancoUrl();
		this.bancoUser = variaveisDeAmbiente.getBancoUser();
		this.bancoSenha = variaveisDeAmbiente.getBancoSenha();
		this.bancoSchema = "main";
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:" + this.bancoUrl);
		dataSource.setUsername(this.bancoUser);
		dataSource.setPassword(this.bancoSenha);

		return dataSource;
	}

	@Bean
	public Flyway flyway() {
		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:" + this.bancoUrl, this.bancoUser, this.bancoSenha)
				.locations("classpath:db/migration")
				.defaultSchema(this.bancoSchema)
				.load();

		flyway.migrate();

		return flyway;
	}

}
