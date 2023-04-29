package br.univille.cofrinho.domains.usuario;

import br.univille.cofrinho.domains.autenticacao.exceptions.LoginOuSenhaInvalidos;
import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

	public UsuarioEntity criarUsuario(String login, String email, String senha){
		if (usuarioRepository.existsByLogin(login)) {
			throw new RegraDeNegocioException("Login existente", HttpStatus.CONFLICT);
		}

		if (usuarioRepository.existsByEmail(email)) {
			throw new RegraDeNegocioException("Email existente", HttpStatus.CONFLICT);
		}

		return this.usuarioRepository.save(new UsuarioEntity(login, senha, email));
	}

	public void deletarPorId (UUID id) {
		this.usuarioRepository.deleteById(id);
	}

}
