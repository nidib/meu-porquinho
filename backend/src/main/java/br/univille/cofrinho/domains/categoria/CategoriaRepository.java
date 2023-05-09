package br.univille.cofrinho.domains.categoria;

import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {

	@Query("SELECT c FROM CategoriaEntity c WHERE c.usuario IS NULL OR c.usuario = ?1 ORDER BY c.tipo ASC, c.nome ASC")
	List<CategoriaEntity> obterCategoriasDisponiveisPor(UsuarioEntity usuario);

	@Query("SELECT c FROM CategoriaEntity c WHERE c.id = ?1 AND c.usuario = ?2")
	Optional<CategoriaEntity> obterCategoriaPor(UUID id, UsuarioEntity usuario);

	@Query("SELECT COUNT (*) > 0 FROM CategoriaEntity c WHERE c.nome = ?1 AND c.tipo = ?2 AND c.usuario = ?3")
	boolean existePor(String nome, TipoCategoriaEnum tipo, UsuarioEntity usuario);

}
