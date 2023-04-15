package br.univille.cofrinho.domains.priority;

public class PrioridadeMapper {

	private PrioridadeMapper() {}

	public static PrioridadeDTO paraDTO(PrioridadeEntity prioridadeEntity) {
		return new PrioridadeDTO(prioridadeEntity.getId(), prioridadeEntity.getNome(), prioridadeEntity.getGrandeza());
	}

}
