package br.univille.cofrinho.controllers.usuario.dtos;

import java.util.UUID;

public record UsuarioResDTO(
	UUID id,
	String login,
	String email
) {}
