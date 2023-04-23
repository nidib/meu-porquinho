package br.univille.cofrinho.exceptions;

import org.springframework.http.HttpStatus;

public class RegraDeNegocioException extends RuntimeException {

	private final HttpStatus codigoHttp;

	public RegraDeNegocioException(String mensagem, HttpStatus codigoHttp) {
		super(mensagem);
		this.codigoHttp = codigoHttp;
	}

	public HttpStatus getCodigoHttp() {
		return this.codigoHttp;
	}

}
