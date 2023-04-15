package br.univille.cofrinho.domains.frequency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FrequenciaRepository extends JpaRepository<FrequenciaEntity, UUID> {}
