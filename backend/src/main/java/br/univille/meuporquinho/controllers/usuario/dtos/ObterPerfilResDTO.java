package br.univille.meuporquinho.controllers.usuario.dtos;

public record ObterPerfilResDTO(
	String nomeCompleto,
	String apelido,
	String dataDeNascimento
) {}
