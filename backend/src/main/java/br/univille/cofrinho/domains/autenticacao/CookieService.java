package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.domains.autenticacao.exceptions.AutenticacaoInvalidaException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class CookieService {

	@Autowired
	private TokenService tokenService;

	public UUID obterUsuarioId(HttpServletRequest request) {
		Cookie cookieDeAutenticacao = this.obterCookieDeAutenticacao(request.getCookies());

		return this.tokenService
			.validarToken(cookieDeAutenticacao.getValue())
			.orElseThrow(AutenticacaoInvalidaException::new);
	}

	private Cookie obterCookieDeAutenticacao(Cookie[] cookies) {
		return Optional.ofNullable(cookies)
			.stream()
			.flatMap(Arrays::stream)
			.filter(cookie -> cookie.getName().equals("auth"))
			.findFirst()
			.orElseThrow(() -> new AutenticacaoInvalidaException("Cookie de autenticação não encontrado"));
	}

}
