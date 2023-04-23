package br.univille.cofrinho.controllers.error.dtos;

import java.util.Set;

public record ErrorResDTO(
	Set<String> erros
) {}
