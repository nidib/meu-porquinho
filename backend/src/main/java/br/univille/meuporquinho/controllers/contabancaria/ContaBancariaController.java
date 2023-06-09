package br.univille.meuporquinho.controllers.contabancaria;

import br.univille.meuporquinho.controllers.contabancaria.dtos.CriarContaBancariaReqDTO;
import br.univille.meuporquinho.controllers.contabancaria.dtos.ContaBancariaResDTO;
import br.univille.meuporquinho.controllers.contabancaria.dtos.EditarContaBancariaReqDTO;
import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaEntity;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

	@GetMapping
	public ResponseEntity<List<ContaBancariaResDTO>> listarContasBancarias(@UsuarioLogadoId UUID usuarioLogadoId) {
		List<ContaBancariaEntity> contasBancarias = this.contaBancariaService.obterDisponiveisPor(usuarioLogadoId);
		List<ContaBancariaResDTO> dtos = contasBancarias
			.stream()
			.map(c -> new ContaBancariaResDTO(c.getId(), c.getTitulo(), c.getSaldo(), c.getDiaDoVencimentoDaFatura()))
			.toList();

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ContaBancariaResDTO> criarContaBancaria(@Valid @RequestBody CriarContaBancariaReqDTO contaBancaria, @UsuarioLogadoId UUID usuarioLogadoId) {
		ContaBancariaEntity contaBancariaCriada = this.contaBancariaService.criar(
			contaBancaria.titulo(), contaBancaria.saldo(), contaBancaria.diaDoVencimentoDaFatura(), usuarioLogadoId
		);
		double saldoEmReaisDecimal = this.contaBancariaService.obterSaldoEmReaisDecimal(contaBancariaCriada.getSaldo());

		return new ResponseEntity<>(
			new ContaBancariaResDTO(contaBancariaCriada.getId(), contaBancariaCriada.getTitulo(), saldoEmReaisDecimal, contaBancariaCriada.getDiaDoVencimentoDaFatura()),
			HttpStatus.CREATED
		);
	}

	@PutMapping
	public ResponseEntity<ContaBancariaResDTO> editarContaBancaria(@Valid @RequestBody EditarContaBancariaReqDTO contaBancaria, @UsuarioLogadoId UUID usuarioLogadoId) {
		ContaBancariaEntity contaBancariaAtualizada = this.contaBancariaService.atualizar(
			contaBancaria.id(),
			contaBancaria.titulo(),
			contaBancaria.saldo(),
			contaBancaria.diaDoVencimentoDaFatura(),
			usuarioLogadoId
		);
		ContaBancariaResDTO dto = new ContaBancariaResDTO(
			contaBancariaAtualizada.getId(),
			contaBancariaAtualizada.getTitulo(),
			this.contaBancariaService.obterSaldoEmReaisDecimal(contaBancariaAtualizada.getSaldo()),
			contaBancariaAtualizada.getDiaDoVencimentoDaFatura()
		);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarContaBancaria(@PathVariable("id") UUID id, @UsuarioLogadoId UUID usuarioLogadoId) {
		this.contaBancariaService.remover(id, usuarioLogadoId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
