package br.univille.cofrinho.exceptions;

public class VariavelDeAmbienteNaoConfiguradaException extends RuntimeException {

	public VariavelDeAmbienteNaoConfiguradaException(String nomeDaVariavel) {
		super("Variável " + nomeDaVariavel + " não configurada");
	}

}
