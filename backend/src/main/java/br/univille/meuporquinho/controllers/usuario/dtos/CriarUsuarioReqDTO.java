package br.univille.meuporquinho.controllers.usuario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CriarUsuarioReqDTO(
	@NotNull(message = "login é obrigatório")
	@NotBlank(message = "login é obrigatório")
	String login,
	@NotNull(message = "email é obrigatório")
	@NotBlank(message = "email é obrigatório")
	String email,
	@NotNull(message = "senha é obrigatória")
	@NotBlank(message = "senha é obrigatória")
	String senha,
	@NotNull(message = "nome não é obrigatório")
	@NotBlank(message = "nome não é obrigatório")
	String nomeCompleto,
	@NotNull(message = "dataDeNascimento não é obrigatório")
	LocalDate dataDeNascimento

) {
}
