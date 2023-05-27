package br.univille.meuporquinho.domains.contabancaria;

import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, UUID> {

	@Query("SELECT c FROM ContaBancariaEntity c WHERE c.titulo = ?1 AND c.usuario = ?2")
	Optional<ContaBancariaEntity> obterPorTituloEUsuario(String titulo, UsuarioEntity usuario);

}
