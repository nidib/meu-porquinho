package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.priority.PrioridadeDTO;
import br.univille.cofrinho.domains.priority.PrioridadeMapper;
import br.univille.cofrinho.domains.priority.PrioridadeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController {

	@Autowired
	private PrioridadeService prioridadeService;

	@GetMapping
	@Operation(description = "Lista todas as prioridades")
	public ResponseEntity<List<PrioridadeDTO>> listarTodasAsPrioridades() {
		List<PrioridadeDTO> prioridades = prioridadeService.obterTodasAsPrioridades()
			.stream()
			.map(PrioridadeMapper::paraDTO)
			.toList();

		return new ResponseEntity<>(prioridades, HttpStatus.OK);
	}

}
