package br.univille.cofrinho.domains.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AutenticacaoConfig implements WebMvcConfigurer {

	private final AutenticacaoInterceptor autenticacaoInterceptor;

	private final UsuarioLogadoHandlerMethodArgumentResolver usuarioLogadoHandlerMethodArgumentResolver;

	@Autowired
	public AutenticacaoConfig(AutenticacaoInterceptor autenticacaoInterceptor) {
		this.autenticacaoInterceptor = autenticacaoInterceptor;
		this.usuarioLogadoHandlerMethodArgumentResolver = new UsuarioLogadoHandlerMethodArgumentResolver();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(this.autenticacaoInterceptor)
			.addPathPatterns("/api/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(this.usuarioLogadoHandlerMethodArgumentResolver);
	}

}
