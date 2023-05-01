package br.univille.cofrinho.domains.usuario;

import br.univille.cofrinho.controllers.usuario.dtos.ListarUsuarioDTO;
import br.univille.cofrinho.domains.autenticacao.exceptions.LoginOuSenhaInvalidos;
import br.univille.cofrinho.domains.perfil.PerfilEntity;
import br.univille.cofrinho.domains.perfil.PerfilService;
import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	private final PerfilService perfilService;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, PerfilService perfilService) {
		this.usuarioRepository = usuarioRepository;
		this.perfilService = perfilService;
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

		UsuarioEntity usuarioCriado = this.usuarioRepository.save(new UsuarioEntity(login, senha, email));

		this.perfilService.criar(new PerfilEntity(usuarioCriado));

		return usuarioCriado;
	}

	public void deletarPorId (UUID id) {
		this.usuarioRepository.deleteById(id);
	}

	public boolean existePorId(UUID id) {
		return this.usuarioRepository.existsById(id);
	}

	public ListarUsuarioDTO listarUsuario(UUID id) {
		Optional<UsuarioEntity> usuario = this.usuarioRepository.findById(id);
		ListarUsuarioDTO usuarioDTO = new ListarUsuarioDTO();
		usuarioDTO.setEmail(usuario.get().getEmail());
		usuarioDTO.setLogin(usuario.get().getLogin());

		return usuarioDTO;
	}
}

