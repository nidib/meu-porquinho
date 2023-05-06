package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.config.VariaveisDeAmbiente;
import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.exceptions.VariavelDeAmbienteNaoConfiguradaException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

	private final JWTVerifier verificadorJwt;

	private final String issuer;

	private final String chave;

	private final String chaveClaim;

	@Autowired
	public TokenService(VariaveisDeAmbiente variaveisDeAmbiente) {
		this.issuer = "Cofrinho";
		this.chaveClaim = "id";
		this.chave = variaveisDeAmbiente.getJwtKey();
		this.verificadorJwt = JWT
			.require(Algorithm.HMAC256(this.chave))
			.build();
	}

	public String gerarToken(UsuarioEntity usuario) {
		return JWT
			.create()
			.withIssuer(this.issuer)
			.withSubject(usuario.getLogin())
			.withClaim(this.chaveClaim, usuario.getId().toString())
			.withExpiresAt(
				LocalDateTime
					.now()
					.plusDays(1)
					.toInstant(ZoneOffset.of("-03:00"))
			)
			.sign(Algorithm.HMAC256(this.chave));
	}

	public Optional<UUID> validarToken(String token) {
		try {
			String prefixoBearer = "Bearer ";
			String jwt = token.substring(prefixoBearer.length());

			return Optional.of(
				this.verificadorJwt.verify(jwt).getClaim(this.chaveClaim).as(UUID.class)
			);
		} catch (JWTVerificationException exception) {
			return Optional.empty();
		}
	}

}
