package br.univille.cofrinho.domains.usuario;

import br.univille.cofrinho.domains.autenticacao.CriptografiaService;
import br.univille.cofrinho.domains.autenticacao.exceptions.LoginOuSenhaInvalidosException;
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

	public UsuarioEntity obterPorLoginESenha(String login, String senhaNaoCriptografada) {
		UsuarioEntity usuario = this.usuarioRepository.findByLogin(login)
			.orElseThrow(LoginOuSenhaInvalidosException::new);

		if (!CriptografiaService.validar(senhaNaoCriptografada, usuario.getSenha())) {
			throw new LoginOuSenhaInvalidosException();
		}

		return usuario;
	}

	public UsuarioEntity criarUsuario(String login, String email, String senhaNaoCriptografada){
		if (usuarioRepository.existsByLogin(login)) {
			throw new RegraDeNegocioException("Login existente", HttpStatus.CONFLICT);
		}

		if (usuarioRepository.existsByEmail(email)) {
			throw new RegraDeNegocioException("Email existente", HttpStatus.CONFLICT);
		}

		PerfilEntity perfilCriado = this.perfilService.criar(new PerfilEntity());
		UsuarioEntity usuario = new UsuarioEntity(login, CriptografiaService.criptografar(senhaNaoCriptografada), email, perfilCriado);

		return this.usuarioRepository.save(usuario);
	}

	public void deletarPorId (UUID id) {
		UsuarioEntity usuario = this.obterUsuario(id);

		this.usuarioRepository.deleteById(id);
		this.perfilService.deletetarPorId(usuario.getPerfil().getId());
	}

	public boolean existePorId(UUID id) {
		return this.usuarioRepository.existsById(id);
	}

	public UsuarioEntity obterUsuario(UUID id) {
		Optional<UsuarioEntity> usuario = this.usuarioRepository.findById(id);

		if (usuario.isEmpty()) {
			throw new RegraDeNegocioException("Usuário não existe", HttpStatus.NOT_FOUND);
		}

		return usuario.get();
	}

}

