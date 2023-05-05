package br.univille.cofrinho.config;

import br.univille.cofrinho.exceptions.VariavelDeAmbienteNaoConfiguradaException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariaveisDeAmbiente {

	private final Dotenv dotenv;

	private final String bancoUrl;

	private final String bancoUser;

	private final String bancoSenha;

	private final String jwtKey;

	public VariaveisDeAmbiente() {
		this.dotenv = Dotenv
			.configure()
			.ignoreIfMissing()
			.load();

		this.bancoUrl = this.getVariavel("DB_URL");
		this.bancoUser = this.getVariavel("DB_USER");
		this.bancoSenha = this.getVariavel("DB_PASSWORD");
		this.jwtKey = this.getVariavel("JWT_KEY");
	}

	private String getVariavel(String variavel) {
		return Optional
			.ofNullable(this.dotenv.get(variavel))
			.orElseThrow(() -> new VariavelDeAmbienteNaoConfiguradaException(variavel));
	}

	public String getBancoUrl() {
		return this.bancoUrl;
	}

	public String getBancoUser() {
		return this.bancoUser;
	}

	public String getBancoSenha() {
		return this.bancoSenha;
	}

	public String getJwtKey() {
		return this.jwtKey;
	}

}
