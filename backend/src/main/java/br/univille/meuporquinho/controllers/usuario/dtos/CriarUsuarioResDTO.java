package br.univille.meuporquinho.controllers.usuario.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CriarUsuarioResDTO(
	UUID id,
	String login,
	String email
) {}
