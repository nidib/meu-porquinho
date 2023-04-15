package br.univille.cofrinho.domains.frequency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequenciaService {

	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	public List<FrequenciaEntity> obterTodasAsFrequencias(){
		return this.frequenciaRepository.findAll();
	}

}
