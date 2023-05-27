package br.univille.meuporquinho.controllers.contabancaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditarContaBancariaReqDTO(
	@NotNull(message = "id é obrigatório")
	UUID id,
	@NotNull(message = "titulo é obrigatório")
	@NotBlank(message = "titulo é obrigatório")
	String titulo,
	@NotNull(message = "saldo é obrigatório")
	Double saldo,
	@NotNull(message = "dia do vencimento da fatura é obrigatório")
	Integer diaDoVencimentoDaFatura
) {}
