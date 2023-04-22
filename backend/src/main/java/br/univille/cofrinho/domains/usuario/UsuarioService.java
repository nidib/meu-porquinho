package br.univille.cofrinho.domains.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<UsuarioEntity> obterPorLoginESenha(String login, String senha) {
		return Optional.ofNullable(
			this.usuarioRepository.findByLoginAndSenha(login, senha)
		);
	}

}
