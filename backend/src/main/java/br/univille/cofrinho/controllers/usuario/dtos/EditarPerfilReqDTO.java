package br.univille.cofrinho.controllers.usuario.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record EditarPerfilReqDTO (
	@Nullable
	String nomeCompleto,
	@Nullable
	@Past(message = "Data deve ser no passado")
	LocalDate dataDeNascimento,
	@Nullable
	String apelido
) {}
