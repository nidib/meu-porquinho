package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping
	@Operation(description = "Cria um usuário")
	public ResponseEntity<Object> criaUsuario(@RequestBody UsuarioEntity usuarioInfo){
		this.usuarioService.criarUsuario(usuarioInfo.getLogin(), usuarioInfo.getEmail(), usuarioInfo.getSenha());

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
