package br.univille.cofrinho.controllers.usuario;

import br.univille.cofrinho.controllers.usuario.dtos.UsuarioReqDTO;
import br.univille.cofrinho.controllers.usuario.dtos.UsuarioResDTO;
import br.univille.cofrinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.cofrinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
	public ResponseEntity<UsuarioResDTO> criaUsuario(@Valid @RequestBody UsuarioReqDTO usuarioInfo) {
		UsuarioEntity usuarioNovo = this.usuarioService.criarUsuario(usuarioInfo.login(), usuarioInfo.email(), usuarioInfo.senha());

		return new ResponseEntity<>(
			new UsuarioResDTO(usuarioNovo.getId(), usuarioNovo.getLogin(), usuarioNovo.getEmail()),
			HttpStatus.CREATED
		);
	}

	@PrecisaEstarLogado
	@DeleteMapping
	@Operation(description = "Deleta o usuário logado")
	public ResponseEntity<Object> deletaUsuario(@UsuarioLogadoId UUID id) {
		this.usuarioService.deletarPorId(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
