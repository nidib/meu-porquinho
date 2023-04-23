package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;

@Component
public class AutenticacaoInterceptor implements HandlerInterceptor {

	private final CookieService cookieService;

	@Autowired
	public AutenticacaoInterceptor(CookieService cookieService) {
		this.cookieService = cookieService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!this.rotaExiste(handler) || !this.rotaPrecisaDeAutenticacao(handler)) {
			return true;
		}

		UUID usuarioIdLogado = this.cookieService.obterUsuarioId(request);

		request.setAttribute(
			UsuarioLogadoHandlerMethodArgumentResolver.usuarioLogadoIdChave,
			usuarioIdLogado.toString()
		);

		return true;
	}

	private boolean rotaExiste(Object rotaHandler) {
		return rotaHandler instanceof HandlerMethod;
	}

	private boolean rotaPrecisaDeAutenticacao(Object rota) {
		HandlerMethod rotaHandler = (HandlerMethod) rota;

		if (rotaHandler.getMethod().getDeclaringClass().getAnnotation(PrecisaEstarLogado.class) != null) {
			return true;
		}

		return rotaHandler.getMethodAnnotation(PrecisaEstarLogado.class) != null;
	}

}
