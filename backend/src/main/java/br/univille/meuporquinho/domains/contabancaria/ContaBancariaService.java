package br.univille.meuporquinho.domains.contabancaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaService {

	private final ContaBancariaRepository contaBancariaRepository;

	@Autowired
	public ContaBancariaService(ContaBancariaRepository contaBancariaRepository) {
		this.contaBancariaRepository = contaBancariaRepository;
	}

}
