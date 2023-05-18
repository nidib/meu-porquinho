package br.univille.meuporquinho.domains.prioridade;

public class PrioridadeMapper {

	private PrioridadeMapper() {}

	public static PrioridadeDTO paraDTO(PrioridadeEntity prioridadeEntity) {
		return new PrioridadeDTO(prioridadeEntity.getId(), prioridadeEntity.getNome(), prioridadeEntity.getGrandeza());
	}

}
