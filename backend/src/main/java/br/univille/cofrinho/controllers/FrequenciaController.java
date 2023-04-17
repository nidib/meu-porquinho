package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.frequency.FrequenciaDTO;
import br.univille.cofrinho.domains.frequency.FrequenciaMapper;
import br.univille.cofrinho.domains.frequency.FrequenciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaController {

	@Autowired
	private FrequenciaService frequenciaService;

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
