package br.univille.meuporquinho.controllers.contabancaria;

import br.univille.meuporquinho.controllers.contabancaria.dtos.CriarContaBancariaReqDTO;
import br.univille.meuporquinho.controllers.contabancaria.dtos.CriarContaBancariaResDTO;
import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaEntity;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PrecisaEstarLogado
@RestController()
@RequestMapping("/api/conta-bancaria")
public class ContaBancariaController {

	private final ContaBancariaService contaBancariaService;

	@Autowired
	public ContaBancariaController(ContaBancariaService contaBancariaService) {
		this.contaBancariaService = contaBancariaService;
	}

	@PostMapping
	public ResponseEntity<CriarContaBancariaResDTO> criarContaBancaria(@Valid @RequestBody CriarContaBancariaReqDTO contaBancaria, @UsuarioLogadoId UUID usuarioLogadoId) {
		ContaBancariaEntity contaBancariaCriada = this.contaBancariaService.criar(
			contaBancaria.titulo(), contaBancaria.saldo(), contaBancaria.diaDoVencimentoDaFatura(), usuarioLogadoId
		);
		double saldoEmReaisDecimal = contaBancariaCriada.getSaldo() / 100.0;

		return new ResponseEntity<>(
			new CriarContaBancariaResDTO(contaBancariaCriada.getId(), contaBancariaCriada.getTitulo(), saldoEmReaisDecimal, contaBancariaCriada.getDiaDoVencimentoDaFatura()),
			HttpStatus.CREATED
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarContaBancaria(@PathVariable("id") UUID id, @UsuarioLogadoId UUID usuarioLogadoId) {
		this.contaBancariaService.remover(id, usuarioLogadoId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
