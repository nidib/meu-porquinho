package br.univille.cofrinho.controllers.usuario.dtos;

public record ObterPerfilResDTO(
	String nomeCompleto,
	String apelido,
	String dataDeNascimento
) {}
