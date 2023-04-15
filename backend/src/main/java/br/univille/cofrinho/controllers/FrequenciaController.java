package br.univille.cofrinho.controllers;

import br.univille.cofrinho.domains.frequency.FrequenciaDTO;
import br.univille.cofrinho.domains.frequency.FrequenciaMapper;
import br.univille.cofrinho.domains.frequency.FrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaController {

	@Autowired
	private FrequenciaService frequenciaService;

	@GetMapping
	public List<FrequenciaDTO> obterTodasAsFrequencias(){
		return this.frequenciaService.obterTodasAsFrequecias()
			.stream()
			.map(FrequenciaMapper::paraDTO)
			.collect(Collectors.toList());
	}

}
