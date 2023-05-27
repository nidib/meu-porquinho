package br.univille.meuporquinho.controllers.contabancaria.dtos;

import java.util.UUID;

public record CriarContaBancariaResDTO (
	UUID id,
	String titulo,
	double saldo,
	int diaDoVencimentoDaFatura
) {}
