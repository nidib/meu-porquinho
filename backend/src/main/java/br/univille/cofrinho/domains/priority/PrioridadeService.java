package br.univille.cofrinho.domains.priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrioridadeService {

	@Autowired
	private PrioridadeRepository prioridadeRepository;

	public List<PrioridadeEntity> obterTodasAsPrioridades() {
		return prioridadeRepository.findAll();
	}

}
