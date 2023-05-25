package br.univille.meuporquinho.domains.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

	@Query("SELECT u FROM UsuarioEntity u WHERE u.login = ?1 OR u.email = ?1")
	Optional<UsuarioEntity> obterPorLoginOuEmail(String loginOuEmail);

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);

}
