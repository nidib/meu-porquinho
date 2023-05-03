package br.univille.cofrinho.domains.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {

	@Query("SELECT c FROM CategoriaEntity c WHERE c.usuarioId IS NULL OR c.usuarioId = ?1 ORDER BY c.tipo ASC, c.titulo ASC")
	List<CategoriaEntity> obterCategoriasDisponiveisPor(UUID usuarioId);

	@Query("SELECT c FROM CategoriaEntity c WHERE c.id = ?1 AND c.usuarioId = ?2")
	Optional<CategoriaEntity> obterCategoriaPor(UUID id, UUID usuarioId);

	@Query("SELECT COUNT (*) > 0 FROM CategoriaEntity c WHERE c.titulo = ?1 AND c.tipo = ?2 AND c.usuarioId = ?3")
	boolean existePor(String titulo, TipoCategoriaEnum tipo, UUID usuarioId);

}
