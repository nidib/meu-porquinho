package br.univille.cofrinho.controllers.login;

import br.univille.cofrinho.controllers.login.dtos.LoginResDTO;
import br.univille.cofrinho.domains.autenticacao.TokenService;
import br.univille.cofrinho.controllers.login.dtos.LoginReqDTO;
import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
public class LoginController {

	private final UsuarioService usuarioService;

	private final TokenService tokenService;

	@Autowired
	public LoginController(UsuarioService usuarioService, TokenService tokenService) {
		this.usuarioService = usuarioService;
		this.tokenService = tokenService;
	}

	@PostMapping
	@Operation(description = "Realiza login, devolvendo o JWT")
	public ResponseEntity<LoginResDTO> login(@Valid @RequestBody LoginReqDTO loginInfo) {
		UsuarioEntity usuario = this.usuarioService.obterPorLoginESenha(loginInfo.login(), loginInfo.senha());
		String token = this.tokenService.gerarToken(usuario);

		return new ResponseEntity<>(new LoginResDTO(token), HttpStatus.OK);
	}

}
