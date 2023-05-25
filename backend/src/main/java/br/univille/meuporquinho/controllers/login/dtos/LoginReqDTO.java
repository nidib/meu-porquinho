package br.univille.meuporquinho.controllers.login.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginReqDTO(
	@NotNull(message = "login ou email é obrigatório")
	@NotBlank(message = "login ou email é obrigatório")
	String loginOuEmail,
	@NotNull(message = "senha é obrigatória")
	@NotBlank(message = "senha é obrigatória")
	String senha
) {}
