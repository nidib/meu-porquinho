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

	public CategoriaEntity obter(UUID id, UUID usuarioId) {
		return this.categoriaRepository.findByIdAndUsuarioId(id, usuarioId)
			.orElseThrow(() -> new RegraDeNegocioException("Categoria não encontrada", HttpStatus.NOT_FOUND));
	}

	public List<CategoriaEntity> obterCategorias(UUID usuarioId) {
		return this.categoriaRepository.findAllByUsuarioIdIsNullOrUsuarioIdOrderByTipoAscTituloAsc(usuarioId);
	}

	public CategoriaEntity criarCategoria(String titulo, TipoCategoriaEnum tipo, UUID usuarioId) {
		return this.categoriaRepository.save(new CategoriaEntity(titulo, tipo, usuarioId));
	}

	public CategoriaEntity atualizarCategoria(UUID id, String titulo, TipoCategoriaEnum tipo, UUID usuarioId) {
		CategoriaEntity categoriaExistente = this.obter(id, usuarioId);

		return this.categoriaRepository.save(
			new CategoriaEntity(categoriaExistente.getId(), titulo, tipo, usuarioId, categoriaExistente.getCriadoEm())
		);
	}

	public void remover(UUID id, UUID usuarioLogadoId) {
		CategoriaEntity categoriaExistente = this.obter(id, usuarioLogadoId);

		this.categoriaRepository.delete(categoriaExistente);
	}

}
