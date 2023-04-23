package br.univille.cofrinho.controllers.error;

import br.univille.cofrinho.controllers.error.dtos.ErrorResDTO;
import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResDTO> excessaoNaoPega(Exception exception) {
		System.out.println(exception.getMessage());
		return new ResponseEntity<>(
			new ErrorResDTO("Algo deu errado"),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResDTO> excessaoPorMetodoNaoSuportado(HttpRequestMethodNotSupportedException exception) {
		return new ResponseEntity<>(
			new ErrorResDTO("Método " + exception.getMethod() + " não suportado"),
			HttpStatus.NOT_FOUND
		);
	}

	@ExceptionHandler(RegraDeNegocioException.class)
	public ResponseEntity<ErrorResDTO> excessaoDeRegraDeNegocio(RegraDeNegocioException exception) {
		return new ResponseEntity<>(
			new ErrorResDTO(exception.getMessage()),
			exception.getCodigoHttp()
		);
	}

}
