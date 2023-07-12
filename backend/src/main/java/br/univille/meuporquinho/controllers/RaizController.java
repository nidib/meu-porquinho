package br.univille.meuporquinho.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RaizController {

	private record OkResDTO(
		boolean ok
	) {}

	@GetMapping
	public ResponseEntity<OkResDTO> index() {
		return new ResponseEntity<>(new OkResDTO(true), HttpStatus.OK);
	}

}
