package br.univille.meuporquinho.domains.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
	Optional<UsuarioEntity> findByLogin(String login);
	boolean existsByLogin(String login);
	boolean existsByEmail(String email);
}
