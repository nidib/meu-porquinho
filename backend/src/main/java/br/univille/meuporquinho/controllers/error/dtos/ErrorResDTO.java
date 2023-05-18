package br.univille.meuporquinho.controllers.error.dtos;

import java.util.Set;

public record ErrorResDTO(
	Set<String> erros
) {}
