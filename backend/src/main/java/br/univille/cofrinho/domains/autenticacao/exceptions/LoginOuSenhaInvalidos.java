package br.univille.cofrinho.domains.autenticacao.exceptions;

import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;

public class LoginOuSenhaInvalidos extends RegraDeNegocioException {

	public LoginOuSenhaInvalidos() {
		super("Login ou senha inválidos", HttpStatus.UNAUTHORIZED);
	}

}
