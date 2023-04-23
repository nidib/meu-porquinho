package br.univille.cofrinho.domains.frequencia;

import java.util.UUID;

public record FrequenciaDTO (
	UUID id,
	String nome,
	int grandeza
) {}
