package br.univille.cofrinho.controllers.login.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginReqDTO(
	@NotNull(message = "login é obrigatório")
	@NotBlank(message = "login é obrigatório")
	String login,
	@NotNull(message = "senha é obrigatória")
	@NotBlank(message = "senha é obrigatória")
	String senha
) {}
