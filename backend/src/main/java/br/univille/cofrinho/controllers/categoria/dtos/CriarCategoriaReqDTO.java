package br.univille.cofrinho.controllers.categoria.dtos;

import br.univille.cofrinho.domains.categoria.TipoCategoriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarCategoriaReqDTO(
	@NotNull(message = "nome é obrigatório")
	@NotBlank(message = "nome é obrigatório")
	String nome,
	@NotNull(message = "tipo é obrigatório")
	TipoCategoriaEnum tipo
) {}
