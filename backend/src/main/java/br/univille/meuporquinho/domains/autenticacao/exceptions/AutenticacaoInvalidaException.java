package br.univille.meuporquinho.domains.autenticacao.exceptions;

import br.univille.meuporquinho.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;

public class AutenticacaoInvalidaException extends RegraDeNegocioException {

	public AutenticacaoInvalidaException() {
		super("Autenticação inválida", HttpStatus.UNAUTHORIZED);
	}

	public AutenticacaoInvalidaException(String mensagem) {
		super(mensagem, HttpStatus.UNAUTHORIZED);
	}

}
