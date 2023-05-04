package br.univille.cofrinho.controllers.categoria.dtos;

import br.univille.cofrinho.domains.categoria.TipoCategoriaEnum;

import java.util.UUID;

public record CategoriaResDTO(
	UUID id,
	String nome,
	TipoCategoriaEnum tipo
) {}
