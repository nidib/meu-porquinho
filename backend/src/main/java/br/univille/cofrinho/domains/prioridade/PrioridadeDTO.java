package br.univille.cofrinho.domains.prioridade;

import java.util.UUID;

public record PrioridadeDTO (
	UUID id,
	String nome,
	int grandeza
) {}
