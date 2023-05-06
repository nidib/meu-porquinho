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

	private String mode;

	private String frontendOrigin;

	public VariaveisDeAmbiente() {
		this.dotenv = Dotenv
			.configure()
			.ignoreIfMissing()
			.load();

		this.mode = this.lerVariavelOpcional("MODE", "PRODUCTION");
		this.bancoUrl = this.lerVariavelObrigatoria("DB_URL");
		this.bancoUser = this.lerVariavelObrigatoria("DB_USER");
		this.bancoSenha = this.lerVariavelObrigatoria("DB_PASSWORD");
		this.jwtKey = this.lerVariavelObrigatoria("JWT_KEY");
		this.frontendOrigin = this.isDevelopment() ? "*" : this.lerVariavelObrigatoria("FRONTEND_ORIGIN");
	}

	private String lerVariavelOpcional(String variavel, String valorPadrao) {
		return this.dotenv.get(variavel, valorPadrao);
	}

	private String lerVariavelObrigatoria(String variavel) {
		return Optional
			.ofNullable(this.dotenv.get(variavel))
			.orElseThrow(() -> new VariavelDeAmbienteNaoConfiguradaException(variavel));
	}

	public boolean isDevelopment() {
		return this.mode.equals("DEVELOPMENT");
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

	public String getFrontendOrigin() {
		return this.frontendOrigin;
	}

}
