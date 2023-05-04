package br.univille.cofrinho.domains.categoria;

import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	private void verificaSeExiste(String nome, TipoCategoriaEnum tipo, UUID usuarioId) {
		if (this.categoriaRepository.existePor(nome, tipo, usuarioId)) {
			throw new RegraDeNegocioException("Essa categoria já existe", HttpStatus.CONFLICT);
		}
	}

	private CategoriaEntity obter(UUID id, UUID usuarioId) {
		return this.categoriaRepository.obterCategoriaPor(id, usuarioId)
			.orElseThrow(() -> new RegraDeNegocioException("Categoria não encontrada", HttpStatus.NOT_FOUND));
	}

	public List<CategoriaEntity> obterDisponiveisPor(UUID usuarioId) {
		return this.categoriaRepository.obterCategoriasDisponiveisPor(usuarioId);
	}

	public CategoriaEntity criar(String nome, TipoCategoriaEnum tipo, UUID usuarioId) {
		verificaSeExiste(nome, tipo, usuarioId);

		return this.categoriaRepository.save(new CategoriaEntity(nome, tipo, usuarioId));
	}

	public CategoriaEntity atualizar(UUID id, String nome, TipoCategoriaEnum tipo, UUID usuarioId) {
		CategoriaEntity categoriaExistente = this.obter(id, usuarioId);

		verificaSeExiste(nome, tipo, usuarioId);

		return this.categoriaRepository.save(
			new CategoriaEntity(categoriaExistente.getId(), nome, tipo, usuarioId, categoriaExistente.getCriadoEm())
		);
	}

	public void remover(UUID id, UUID usuarioLogadoId) {
		CategoriaEntity categoriaExistente = this.obter(id, usuarioLogadoId);

		this.categoriaRepository.delete(categoriaExistente);
	}

}
