package br.univille.cofrinho.domains.categoria;

import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import br.univille.cofrinho.domains.usuario.UsuarioService;
import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

	private final UsuarioService usuarioService;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository, UsuarioService usuarioService) {
		this.categoriaRepository = categoriaRepository;
		this.usuarioService = usuarioService;
	}

	private void verificaSeExiste(String nome, TipoCategoriaEnum tipo, UsuarioEntity usuario) {
		if (this.categoriaRepository.existePor(nome, tipo, usuario)) {
			throw new RegraDeNegocioException("Essa categoria já existe", HttpStatus.CONFLICT);
		}
	}

	private CategoriaEntity obter(UUID id, UsuarioEntity usuario) {
		return this.categoriaRepository.obterCategoriaPor(id, usuario)
			.orElseThrow(() -> new RegraDeNegocioException("Categoria não encontrada", HttpStatus.NOT_FOUND));
	}

	public List<CategoriaEntity> obterDisponiveisPor(UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);

		return this.categoriaRepository.obterCategoriasDisponiveisPor(usuario);
	}

	public CategoriaEntity criar(String nome, TipoCategoriaEnum tipo, UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);

		verificaSeExiste(nome, tipo, usuario);

		return this.categoriaRepository.save(new CategoriaEntity(nome, tipo, usuario));
	}

	public CategoriaEntity atualizar(UUID id, String nome, TipoCategoriaEnum tipo, UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);
		CategoriaEntity categoriaExistente = this.obter(id, usuario);

		verificaSeExiste(nome, tipo, usuario);

		CategoriaEntity categoriaAtualizada = new CategoriaEntity(categoriaExistente.getId(), nome, tipo, usuario, categoriaExistente.getCriadoEm());

		return this.categoriaRepository.save(categoriaAtualizada);
	}

	public void remover(UUID id, UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);
		CategoriaEntity categoriaExistente = this.obter(id, usuario);

		this.categoriaRepository.delete(categoriaExistente);
	}

}
