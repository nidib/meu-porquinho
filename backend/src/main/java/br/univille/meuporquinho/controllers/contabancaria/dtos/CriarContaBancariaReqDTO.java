package br.univille.meuporquinho.controllers.contabancaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarContaBancariaReqDTO (
	@NotNull(message = "titulo é obrigatório")
	@NotBlank(message = "titulo é obrigatório")
	String titulo,
	@NotNull(message = "saldo é obrigatório")
	Double saldo,
	@NotNull(message = "dia do vencimento da fatura é obrigatório")
	Integer diaDoVencimentoDaFatura
) {}
