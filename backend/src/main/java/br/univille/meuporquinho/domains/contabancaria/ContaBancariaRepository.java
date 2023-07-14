package br.univille.meuporquinho.domains.contabancaria;

import br.univille.meuporquinho.domains.contabancaria.repositorydtos.SaldoContas;
import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, UUID> {

	@Query("SELECT c FROM ContaBancariaEntity c WHERE c.usuario = ?1")
	List<ContaBancariaEntity> obterTodasPorUsuario(UsuarioEntity usuario);

	@Query("SELECT c FROM ContaBancariaEntity c WHERE c.usuario = ?1 ORDER BY c.saldo DESC")
	List<ContaBancariaEntity> obterTodasPorUsuarioOrdenadasPorSaldo(UsuarioEntity usuario);

	@Query("SELECT COUNT (*) > 0 FROM ContaBancariaEntity c WHERE c.titulo = ?1 AND c.usuario = ?2")
	boolean existePor(String titulo, UsuarioEntity usuario);

	@Query("SELECT c FROM ContaBancariaEntity c WHERE c.id = ?1 AND c.usuario = ?2")
	Optional<ContaBancariaEntity> obterPorIdEUsuario(UUID id, UsuarioEntity usuario);

	@Query("SELECT new br.univille.meuporquinho.domains.contabancaria.repositorydtos.SaldoContas(" +
		"SUM(CASE WHEN c.saldo > 0 THEN c.saldo ELSE 0 END), " +
		"SUM(CASE WHEN c.saldo < 0 THEN c.saldo ELSE 0 END)" +
		") " +
		"FROM ContaBancariaEntity c " +
		"WHERE c.usuario = ?1"
	)
	 SaldoContas obterSaldoDasContas(UsuarioEntity usuario);

}
