package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.autenticacao.TokenService;
import br.univille.cofrinho.domains.autenticacao.dtos.LoginReqDTO;
import br.univille.cofrinho.domains.autenticacao.exceptions.LoginOuSenhaInvalidos;
import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

	private final UsuarioService usuarioService;

	private final TokenService tokenService;

	@Autowired
	public LoginController(UsuarioService usuarioService, TokenService tokenService) {
		this.usuarioService = usuarioService;
		this.tokenService = tokenService;
	}

	@PostMapping
	@Operation(description = "Fazer login. Setando um cookie com a chave de autenticação")
	public ResponseEntity<Object> login(@RequestBody LoginReqDTO loginInfo, HttpServletResponse response) {
		UsuarioEntity usuario = this.usuarioService
			.obterPorLoginESenha(loginInfo.login(), loginInfo.senha())
			.orElseThrow(LoginOuSenhaInvalidos::new);

		response.addCookie(new Cookie("auth", tokenService.gerarToken(usuario)));

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
