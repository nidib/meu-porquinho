package br.univille.cofrinho.domains.frequencia;

public class FrequenciaMapper {

	private FrequenciaMapper() {}

	public static FrequenciaDTO paraDTO(FrequenciaEntity frequenciaEntity) {
		return new FrequenciaDTO(frequenciaEntity.getId(), frequenciaEntity.getNome(), frequenciaEntity.getGrandeza());
	}

}
