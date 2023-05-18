package br.univille.meuporquinho.controllers.error;

import br.univille.meuporquinho.config.VariaveisDeAmbiente;
import br.univille.meuporquinho.controllers.error.dtos.ErrorResDTO;
import br.univille.meuporquinho.exceptions.RegraDeNegocioException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
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

	private final VariaveisDeAmbiente variaveisDeAmbiente;

	@Autowired
	public ErrorController(VariaveisDeAmbiente variaveisDeAmbiente) {
		this.variaveisDeAmbiente = variaveisDeAmbiente;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResDTO> excessaoNaoPega(Exception exception) {
		Set<String> erros;

		if (this.variaveisDeAmbiente.isDevelopment()) {
			erros = Set.of(exception.getClass().getName() + ": " + exception.getMessage());
			System.out.println(exception.getMessage());
		} else {
			erros = Set.of("Algo deu errado");
		}

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
