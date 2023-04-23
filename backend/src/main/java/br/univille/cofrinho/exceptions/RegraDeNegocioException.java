package br.univille.cofrinho.exceptions;

import org.springframework.http.HttpStatus;

public class RegraDeNegocioException extends RuntimeException {

	private final HttpStatus codigoHttp;

	public RegraDeNegocioException(String message, HttpStatus codigoHttp) {
		super(message);
		this.codigoHttp = codigoHttp;
	}

	public HttpStatus getCodigoHttp() {
		return this.codigoHttp;
	}

}
