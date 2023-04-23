package br.univille.cofrinho.domains.prioridade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrioridadeService {

	private final PrioridadeRepository prioridadeRepository;

	@Autowired
	public PrioridadeService(PrioridadeRepository prioridadeRepository) {
		this.prioridadeRepository = prioridadeRepository;
	}

	public List<PrioridadeEntity> obterTodasAsPrioridades() {
		Sort ordenarCrescentePorGrandeza = Sort.by(Sort.Direction.ASC, "grandeza");

		return prioridadeRepository.findAll(ordenarCrescentePorGrandeza);
	}

}
