package br.univille.cofrinho.domains.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AutenticacaoConfig implements WebMvcConfigurer {

	@Autowired
	private AutenticacaoInterceptor autenticacaoInterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(this.autenticacaoInterceptor)
			.addPathPatterns("/api/**")
			.excludePathPatterns(List.of(
				"/api/login"
			));
	}

}