package br.univille.meuporquinho.domains.autenticacao;

import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.autenticacao.annotations.UsuarioLogadoId;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

public class UsuarioLogadoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	protected static final String usuarioLogadoIdChave = "usuarioLogadoId";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.getMethod() == null) {
			return false;
		}

		if (
			parameter.getMethod().getAnnotation(PrecisaEstarLogado.class) == null &&
			parameter.getMethod().getDeclaringClass().getAnnotation(PrecisaEstarLogado.class) == null
		) {
			return false;
		}

		return parameter.hasParameterAnnotation(UsuarioLogadoId.class) && parameter.getParameterType().equals(UUID.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

		return UUID.fromString(request.getAttribute(usuarioLogadoIdChave).toString());
	}

}
