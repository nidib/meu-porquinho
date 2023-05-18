package br.univille.meuporquinho.exceptions;

public class VariavelDeAmbienteNaoConfiguradaException extends RuntimeException {

	public VariavelDeAmbienteNaoConfiguradaException(String nomeDaVariavel) {
		super("Variável " + nomeDaVariavel + " não configurada");
	}

}
