package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.prioridade.PrioridadeDTO;
import br.univille.cofrinho.domains.prioridade.PrioridadeMapper;
import br.univille.cofrinho.domains.prioridade.PrioridadeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/prioridade")
public class PrioridadeController {

	@Autowired
	private PrioridadeService prioridadeService;

	@GetMapping
	@Operation(description = "Lista todas as prioridades")
	public ResponseEntity<List<PrioridadeDTO>> listarTodasAsPrioridades(HttpServletRequest request) {
		List<PrioridadeDTO> prioridades = prioridadeService.obterTodasAsPrioridades()
			.stream()
			.map(PrioridadeMapper::paraDTO)
			.toList();

		return new ResponseEntity<>(prioridades, HttpStatus.OK);
	}

}
