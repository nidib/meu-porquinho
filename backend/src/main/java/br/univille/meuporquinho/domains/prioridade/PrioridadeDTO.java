package br.univille.meuporquinho.domains.prioridade;

import java.util.UUID;

public record PrioridadeDTO (
	UUID id,
	String nome,
	int grandeza
) {}
