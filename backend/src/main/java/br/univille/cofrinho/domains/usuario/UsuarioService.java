package br.univille.cofrinho.domains.usuario;

import br.univille.cofrinho.domains.autenticacao.exceptions.LoginOuSenhaInvalidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioEntity obterPorLoginESenha(String login, String senha) {
		return this.usuarioRepository.findByLoginAndSenha(login, senha).orElseThrow(LoginOuSenhaInvalidos::new);
	}

}
