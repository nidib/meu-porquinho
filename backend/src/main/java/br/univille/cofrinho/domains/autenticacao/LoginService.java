package br.univille.cofrinho.domains.autenticacao;

import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	private final UsuarioService usuarioService;

	private final TokenService tokenService;

	@Autowired
	public LoginService(UsuarioService usuarioService, TokenService tokenService) {
		this.usuarioService = usuarioService;
		this.tokenService = tokenService;
	}

	public String obterTokenDaSessao(String login, String senha) {
		UsuarioEntity usuario = this.usuarioService.obterPorLoginESenha(login, senha);

		return this.tokenService.gerarToken(usuario);
	}

}
