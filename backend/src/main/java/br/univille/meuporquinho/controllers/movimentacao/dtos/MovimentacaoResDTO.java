package br.univille.meuporquinho.controllers.movimentacao.dtos;

import br.univille.meuporquinho.controllers.categoria.dtos.CategoriaResDTO;
import br.univille.meuporquinho.controllers.contabancaria.dtos.ContaBancariaResDTO;
import br.univille.meuporquinho.domains.frequencia.FrequenciaDTO;
import br.univille.meuporquinho.domains.prioridade.PrioridadeDTO;

import java.util.UUID;

public record MovimentacaoResDTO(
	UUID id,
	String nome,
	double valor,
	String data,
	String dataDeConclusao,
	ContaBancariaResDTO contaBancaria,
	CategoriaResDTO categoria,
	PrioridadeDTO prioridade,
	FrequenciaDTO frequencia
) {}
