package br.univille.meuporquinho.controllers;

import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.frequencia.FrequenciaDTO;
import br.univille.meuporquinho.domains.frequencia.FrequenciaMapper;
import br.univille.meuporquinho.domains.frequencia.FrequenciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PrecisaEstarLogado
@RestController
@RequestMapping("api/frequencia")
public class FrequenciaController {

	private final FrequenciaService frequenciaService;

	@Autowired
	public FrequenciaController(FrequenciaService frequenciaService) {
		this.frequenciaService = frequenciaService;
	}

	@GetMapping
	@Operation(description = "Lista todas as frequências")
	public ResponseEntity<List<FrequenciaDTO>> listarTodasAsFrequencias(){
		List<FrequenciaDTO> frequencias = this.frequenciaService.obterTodasAsFrequencias()
			.stream()
			.map(FrequenciaMapper::paraDTO)
			.toList();

		return new ResponseEntity<>(frequencias, HttpStatus.OK);
	}

}
