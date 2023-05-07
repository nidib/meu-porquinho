package br.univille.cofrinho.controllers.usuario.dtos;

public record ObterUsuarioResDTO(
	String login,
	String email,
	ObterPerfilResDTO perfil
) {}
