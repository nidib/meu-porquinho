package br.univille.cofrinho.domains.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, UUID> {

	Optional<PerfilEntity> findByUsuarioId(UUID usuarioId);

}
