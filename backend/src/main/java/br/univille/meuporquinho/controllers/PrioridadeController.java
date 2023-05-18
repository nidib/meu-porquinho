package br.univille.meuporquinho.controllers;

import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.prioridade.PrioridadeDTO;
import br.univille.meuporquinho.domains.prioridade.PrioridadeMapper;
import br.univille.meuporquinho.domains.prioridade.PrioridadeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PrecisaEstarLogado
@RestController
@RequestMapping("api/prioridade")
public class PrioridadeController {

	private final PrioridadeService prioridadeService;

	@Autowired
	public PrioridadeController(PrioridadeService prioridadeService) {
		this.prioridadeService = prioridadeService;
	}

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
