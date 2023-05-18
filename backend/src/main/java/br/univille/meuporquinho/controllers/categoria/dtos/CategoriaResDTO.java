package br.univille.meuporquinho.controllers.categoria.dtos;

import br.univille.meuporquinho.domains.categoria.TipoCategoriaEnum;

import java.util.UUID;

public record CategoriaResDTO(
	UUID id,
	String nome,
	TipoCategoriaEnum tipo
) {}
