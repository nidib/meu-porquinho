package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.priority.PrioridadeDTO;
import br.univille.cofrinho.domains.priority.PrioridadeMapper;
import br.univille.cofrinho.domains.priority.PrioridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController {

	@Autowired
	private PrioridadeService prioridadeService;

	@GetMapping
	public List<PrioridadeDTO> obterTodasPrioridades() {
		return prioridadeService.obterTodasPrioridades()
			.stream()
			.map(PrioridadeMapper::paraDTO)
			.collect(Collectors.toList());
	}

}
