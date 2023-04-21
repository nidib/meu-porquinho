package br.univille.cofrinho.domains.frequencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequenciaService {

	@Autowired
	private FrequenciaRepository frequenciaRepository;

	public List<FrequenciaEntity> obterTodasAsFrequencias(){
		Sort ordenarCrescentePorGrandeza = Sort.by(Sort.Direction.ASC, "grandeza");

		return this.frequenciaRepository.findAll(ordenarCrescentePorGrandeza);
	}

}
