package br.univille.meuporquinho;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Meu Porquinho",
		description = "Gerenciador financeiro"
	)
)
public class MeuPorquinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuPorquinhoApplication.class, args);
	}

}
