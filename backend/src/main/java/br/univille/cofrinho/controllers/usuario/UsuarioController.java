package br.univille.cofrinho.controllers.usuario;

import br.univille.cofrinho.controllers.usuario.dtos.*;
import br.univille.cofrinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.cofrinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.cofrinho.domains.perfil.PerfilEntity;
import br.univille.cofrinho.domains.perfil.PerfilService;
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

	private final PerfilService perfilService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, PerfilService perfilService) {
		this.usuarioService = usuarioService;
		this.perfilService = perfilService;
	}

	@PostMapping
	@Operation(description = "Cria um usuário")
	public ResponseEntity<CriarUsuarioResDTO> criaUsuario(@Valid @RequestBody CriarUsuarioReqDTO usuarioInfo) {
		UsuarioEntity usuarioNovo = this.usuarioService.criarUsuario(usuarioInfo.login(), usuarioInfo.email(), usuarioInfo.senha());

		return new ResponseEntity<>(
			new CriarUsuarioResDTO(usuarioNovo.getId(), usuarioNovo.getLogin(), usuarioNovo.getEmail()),
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

	@PrecisaEstarLogado
	@PutMapping("/perfil")
	@Operation(description = "Edita o perfil do usuário logado")
	public ResponseEntity<EditarPerfilResDTO> editarPerfil(@Valid @RequestBody EditarPerfilReqDTO perfil, @UsuarioLogadoId UUID usuarioLogadoId) {
		PerfilEntity perfilEditado = this.perfilService.atualizar(
			usuarioLogadoId,
			perfil.nomeCompleto(),
			perfil.dataDeNascimento(),
			perfil.apelido()
		);

		return new ResponseEntity<>(
			new EditarPerfilResDTO(perfilEditado.getNomeCompleto(), perfilEditado.getDataDeNascimento(), perfilEditado.getApelido()),
			HttpStatus.OK
		);
	}

	@PrecisaEstarLogado
	@GetMapping
	@Operation(description = "Exibe informações do usuário logado")
	public ResponseEntity<ObterUsuarioResDTO> obterUsuario(@UsuarioLogadoId UUID id) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(id);
		ObterUsuarioResDTO usuarioDTO = new ObterUsuarioResDTO(usuario.getEmail(), usuario.getLogin());

		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
	}

}
