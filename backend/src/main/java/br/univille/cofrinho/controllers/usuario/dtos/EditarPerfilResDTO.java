package br.univille.cofrinho.controllers.usuario.dtos;

import java.time.LocalDate;

public record EditarPerfilResDTO(
	String nomeCompleto,
	LocalDate dataDeNascimento,
	String apelido
) {}
