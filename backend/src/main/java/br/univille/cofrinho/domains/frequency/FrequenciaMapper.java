package br.univille.cofrinho.domains.frequency;

public class FrequenciaMapper {

	private FrequenciaMapper() {}

	public static FrequenciaDTO paraDTO(FrequenciaEntity frequenciaEntity) {
		return new FrequenciaDTO(frequenciaEntity.id, frequenciaEntity.nome, frequenciaEntity.ordem);
	}

}
