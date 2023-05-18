package br.univille.meuporquinho.controllers.categoria.dtos;

import br.univille.meuporquinho.domains.categoria.TipoCategoriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditarCategoriaReqDTO(
	@NotNull(message = "id é obrigatório")
	UUID id,
	@NotNull(message = "nome é obrigatório")
	@NotBlank(message = "nome é obrigatório")
	String nome,
	@NotNull(message = "tipo é obrigatório")
	TipoCategoriaEnum tipo
) {}
