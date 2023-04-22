package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.domains.autenticacao.exceptions.AutenticacaoInvalidaException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;

@Component
public class AutenticacaoInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return false;
		}

		Cookie[] cookies = Optional
			.ofNullable(request.getCookies())
			.orElseThrow(AutenticacaoInvalidaException::new);

		String token = Arrays.stream(cookies)
			.filter(cookie -> cookie.getName().equals("auth"))
			.findFirst()
			.orElseThrow(AutenticacaoInvalidaException::new)
			.getValue();

		UUID usuarioIdLogado = this.tokenService
			.validarToken(token)
			.orElseThrow(AutenticacaoInvalidaException::new);

		request.setAttribute("usuarioIdLogado", usuarioIdLogado.toString());

		return true;
	}

}
