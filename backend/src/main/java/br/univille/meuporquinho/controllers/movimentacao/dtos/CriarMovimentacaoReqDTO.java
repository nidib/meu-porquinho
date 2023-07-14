package br.univille.meuporquinho.controllers.movimentacao.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CriarMovimentacaoReqDTO(
	String nome,
	long valor,
	LocalDate data,
	LocalDate dataDeConclusao,
	UUID contaBancariaId,
	UUID categoriaId
) {}
