package br.univille.meuporquinho.controllers.usuario.dtos;

public record ObterUsuarioResDTO(
	String login,
	String email,
	ObterPerfilResDTO perfil
) {}
