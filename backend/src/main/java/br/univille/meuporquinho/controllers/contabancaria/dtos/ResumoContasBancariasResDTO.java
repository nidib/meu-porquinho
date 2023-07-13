package br.univille.meuporquinho.controllers.contabancaria.dtos;

import java.util.UUID;

public record ResumoContasBancariasResDTO(
	UUID id,
	String nome,
	double saldo
) {}
