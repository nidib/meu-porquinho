package br.univille.cofrinho.domains.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {

	List<CategoriaEntity> findAllByUsuarioIdIsNullOrUsuarioIdOrderByTipoAscTituloAsc(UUID usuarioId);

	Optional<CategoriaEntity> findByIdAndUsuarioId(UUID id, UUID usuarioLogadoId);

}
