package br.univille.meuporquinho.domains.contabancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, UUID> {
}
