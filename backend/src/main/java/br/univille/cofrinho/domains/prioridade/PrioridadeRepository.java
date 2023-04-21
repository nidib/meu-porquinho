package br.univille.cofrinho.domains.prioridade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrioridadeRepository extends JpaRepository<PrioridadeEntity, UUID> {}
