package br.univille.meuporquinho.controllers.movimentacao.dtos;

import br.univille.meuporquinho.controllers.categoria.dtos.CategoriaResDTO;
import br.univille.meuporquinho.controllers.contabancaria.dtos.ContaBancariaResDTO;

import java.time.LocalDate;
import java.util.UUID;

public record CriarMovimentacaoResDTO(

	UUID id,
	String nome,
	double valor,
	String data,
	String dataDeConclusao,
	ContaBancariaResDTO contaBancaria,
	CategoriaResDTO categoria
) {}
