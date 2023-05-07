package br.univille.cofrinho.controllers.usuario.dtos;

import java.time.LocalDate;

public record ObterPerfilResDTO(
	String nomeCompleto,
	String apelido,
	String dataDeNascimento
) {}
