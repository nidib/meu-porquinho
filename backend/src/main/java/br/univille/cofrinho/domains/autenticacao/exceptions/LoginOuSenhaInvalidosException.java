package br.univille.cofrinho.domains.autenticacao.exceptions;

import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;

public class LoginOuSenhaInvalidosException extends RegraDeNegocioException {

	public LoginOuSenhaInvalidosException() {
		super("Login ou senha inválidos", HttpStatus.UNAUTHORIZED);
	}

}
