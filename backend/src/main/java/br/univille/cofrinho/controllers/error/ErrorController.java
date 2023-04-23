package br.univille.cofrinho.controllers.error;

import br.univille.cofrinho.controllers.error.dtos.ErrorResDTO;
import br.univille.cofrinho.exceptions.RegraDeNegocioException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResDTO> excessaoNaoPega(Exception exception) {
		Set<String> erros = Set.of("Algo deu errado");
		System.out.println(exception.getMessage());

		return new ResponseEntity<>(
			new ErrorResDTO(erros),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResDTO> excessaoPorMetodoNaoSuportado(HttpRequestMethodNotSupportedException exception) {
		Set<String> erros = Set.of("Método " + exception.getMethod() + " não suportado");

		return new ResponseEntity<>(
			new ErrorResDTO(erros),
			HttpStatus.NOT_FOUND
		);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResDTO> excessaoDeValidacao(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		Set<String> erros = new HashSet<>();

		for (ConstraintViolation<?> violation : violations) {
			erros.add(violation.getMessage());
		}

		return new ResponseEntity<>(
			new ErrorResDTO(erros),
			HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResDTO> excessaoDeValidacao(MethodArgumentNotValidException exception) {
		Set<String> erros = exception.getBindingResult().getAllErrors()
			.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.toSet());

		return new ResponseEntity<>(
			new ErrorResDTO(erros),
			HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(RegraDeNegocioException.class)
	public ResponseEntity<ErrorResDTO> excessaoDeRegraDeNegocio(RegraDeNegocioException exception) {
		Set<String> erros = Set.of(exception.getMessage());

		return new ResponseEntity<>(
			new ErrorResDTO(erros),
			exception.getCodigoHttp()
		);
	}

}
