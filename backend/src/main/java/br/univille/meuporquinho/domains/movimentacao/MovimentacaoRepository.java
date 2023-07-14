package br.univille.meuporquinho.domains.movimentacao;

import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, UUID> {

	@Query("SELECT m FROM MovimentacaoEntity m WHERE m.contaBancaria.usuario = ?1 ORDER BY m.data DESC")
	List<MovimentacaoEntity> obterTudoOrdenadoPorData(UsuarioEntity usuario);

}
