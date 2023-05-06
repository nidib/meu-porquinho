package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.config.VariaveisDeAmbiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {

	private final VariaveisDeAmbiente variaveisDeAmbiente;

	@Autowired
	public CorsConfig(VariaveisDeAmbiente variaveisDeAmbiente) {
		this.variaveisDeAmbiente = variaveisDeAmbiente;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedOrigins(this.variaveisDeAmbiente.getFrontendOrigin());
	}

}
