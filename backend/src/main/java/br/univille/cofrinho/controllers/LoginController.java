package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.autenticacao.TokenService;
import br.univille.cofrinho.domains.autenticacao.dtos.LoginReqDTO;
import br.univille.cofrinho.domains.autenticacao.dtos.LoginResDTO;
import br.univille.cofrinho.domains.autenticacao.exceptions.LoginOuSenhaInvalidos;
import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<LoginResDTO> login(@RequestBody LoginReqDTO loginInfo) {
		UsuarioEntity usuario = this.usuarioService
			.obterPorLoginESenha(loginInfo.login(), loginInfo.senha())
			.orElseThrow(LoginOuSenhaInvalidos::new);

		return new ResponseEntity<>(
			new LoginResDTO(tokenService.gerarToken(usuario)),
			HttpStatus.OK
		);
	}

}
