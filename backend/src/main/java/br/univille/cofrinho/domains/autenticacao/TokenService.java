package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

	private static final Dotenv env = Dotenv
		.configure()
		.ignoreIfMissing()
		.load();

	private final String ISSUER = "Cofrinho";

	private final String CHAVE = Optional
		.ofNullable(env.get("JWT_KEY"))
		.orElseThrow(() -> new RuntimeException("Missing JWT_KEY"));

	private final String CHAVE_CLAIM = "id";

	private final JWTVerifier verificadorJwt;

	public TokenService() {
		this.verificadorJwt = JWT
			.require(Algorithm.HMAC256(this.CHAVE))
			.build();
	}

	public String gerarToken(UsuarioEntity usuario) {
		return JWT
			.create()
			.withIssuer(this.ISSUER)
			.withSubject(usuario.getLogin())
			.withClaim(this.CHAVE_CLAIM, usuario.getId().toString())
			.withExpiresAt(
				LocalDateTime
					.now()
					.plusDays(1)
					.toInstant(ZoneOffset.of("-03:00"))
			)
			.sign(Algorithm.HMAC256(this.CHAVE));
	}

	public Optional<UUID> validarToken(String token) {
		try {
			return Optional.of(
				this.verificadorJwt.verify(token).getClaim(this.CHAVE_CLAIM).as(UUID.class)
			);
		} catch (JWTVerificationException exception) {
			return Optional.empty();
		}
	}

}
