package br.univille.cofrinho.controllers.usuario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarUsuarioReqDTO(
	@NotNull(message = "login é obrigatório")
	@NotBlank(message = "login é obrigatório")
	String login,
	@NotNull(message = "email é obrigatório")
	@NotBlank(message = "email é obrigatório")
	String email,
	@NotNull(message = "senha é obrigatória")
	@NotBlank(message = "senha é obrigatória")
	String senha
) {}
