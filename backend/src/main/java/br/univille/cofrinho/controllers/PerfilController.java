package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.perfil.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario/")
public class PerfilController {

	private final PerfilService perfilService;

	@Autowired
	public PerfilController(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	@PostMapping
	public void editar() {}

}
