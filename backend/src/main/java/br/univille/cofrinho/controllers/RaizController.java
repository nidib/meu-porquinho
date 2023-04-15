package br.univille.cofrinho.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaizController {

	@GetMapping("/") 
	public String index() {
		return "ok";
	}

}