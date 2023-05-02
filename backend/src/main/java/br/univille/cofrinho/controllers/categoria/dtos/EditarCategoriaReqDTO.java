package br.univille.cofrinho.controllers.categoria.dtos;

import br.univille.cofrinho.domains.categoria.TipoCategoriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditarCategoriaReqDTO(
	@NotNull(message = "id é obrigatório")
	@NotBlank(message = "id é obrigatório")
	UUID id,
	@NotNull(message = "titulo é obrigatório")
	@NotBlank(message = "titulo é obrigatório")
	String titulo,
	@NotNull(message = "tipo é obrigatório")
	@NotBlank(message = "tipo é obrigatório")
	TipoCategoriaEnum tipo
) {}
