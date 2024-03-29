package br.univille.meuporquinho.controllers.movimentacao;

import br.univille.meuporquinho.controllers.categoria.dtos.CategoriaResDTO;
import br.univille.meuporquinho.controllers.categoria.dtos.CriarCategoriaReqDTO;
import br.univille.meuporquinho.controllers.contabancaria.dtos.ContaBancariaResDTO;
import br.univille.meuporquinho.controllers.movimentacao.dtos.CriarMovimentacaoReqDTO;
import br.univille.meuporquinho.controllers.movimentacao.dtos.CriarMovimentacaoResDTO;
import br.univille.meuporquinho.controllers.movimentacao.dtos.MovimentacaoResDTO;
import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.meuporquinho.domains.categoria.CategoriaService;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaService;
import br.univille.meuporquinho.domains.frequencia.FrequenciaDTO;
import br.univille.meuporquinho.domains.movimentacao.MovimentacaoEntity;
import br.univille.meuporquinho.domains.movimentacao.MovimentacaoService;
import br.univille.meuporquinho.domains.prioridade.PrioridadeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@PrecisaEstarLogado
@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

	private final MovimentacaoService movimentacaoService;

	private final ContaBancariaService contaBancariaService;

	@Autowired
	public MovimentacaoController(MovimentacaoService movimentacaoService, ContaBancariaService contaBancariaService) {
		this.movimentacaoService = movimentacaoService;
		this.contaBancariaService = contaBancariaService;
	}

	@GetMapping
	public ResponseEntity<List<MovimentacaoResDTO>> listarMovimentacoes(@UsuarioLogadoId UUID usuarioLogadoId) {
		List<MovimentacaoResDTO> movimentacoes = this.movimentacaoService.obterTudo(usuarioLogadoId)
			.stream()
			.map(movimentacao -> new MovimentacaoResDTO(
				movimentacao.getId(),
				movimentacao.getNome(),
				this.contaBancariaService.obterSaldoEmReaisDecimal(movimentacao.getValor()),
				movimentacao.getData().toString(),
				movimentacao.getDataDeConclusao() != null ? movimentacao.getDataDeConclusao().toString() : null,
				new ContaBancariaResDTO(
					movimentacao.getContaBancaria().getId(),
					movimentacao.getContaBancaria().getTitulo(),
					movimentacao.getContaBancaria().getSaldo(),
					movimentacao.getContaBancaria().getDiaDoVencimentoDaFatura()
				),
				new CategoriaResDTO(
					movimentacao.getCategoria().getId(),
					movimentacao.getCategoria().getNome(),
					movimentacao.getCategoria().getTipo()
				),
				movimentacao.getPrioridade() != null ?
					new PrioridadeDTO(
						movimentacao.getPrioridade().getId(),
						movimentacao.getPrioridade().getNome(),
						movimentacao.getPrioridade().getGrandeza()
					) : null,
				movimentacao.getFrequencia() != null ?
					new FrequenciaDTO(
						movimentacao.getFrequencia().getId(),
						movimentacao.getFrequencia().getNome(),
						movimentacao.getFrequencia().getGrandeza()
					) : null
			))
			.toList();

		return new ResponseEntity<>(movimentacoes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CriarMovimentacaoResDTO> criar(@Valid @RequestBody CriarMovimentacaoReqDTO movimentacao, @UsuarioLogadoId UUID usuarioLogadoId) {
		MovimentacaoEntity movimentacaoCriada = this.movimentacaoService.criar(
			movimentacao.nome(),
			movimentacao.valor(),
			movimentacao.data(),
			movimentacao.dataDeConclusao(),
			movimentacao.contaBancariaId(),
			movimentacao.categoriaId(),
			usuarioLogadoId
		);

		return new ResponseEntity<>(
			new CriarMovimentacaoResDTO(
				movimentacaoCriada.getId(),
				movimentacaoCriada.getNome(),
				this.contaBancariaService.obterSaldoEmReaisDecimal(movimentacaoCriada.getValor()),
				movimentacaoCriada.getData().toString(),
				movimentacaoCriada.getDataDeConclusao() != null ? movimentacaoCriada.getDataDeConclusao().toString() : null,
				new ContaBancariaResDTO(
					movimentacaoCriada.getContaBancaria().getId(),
					movimentacaoCriada.getContaBancaria().getTitulo(),
					movimentacaoCriada.getContaBancaria().getSaldo(),
					movimentacaoCriada.getContaBancaria().getDiaDoVencimentoDaFatura()
				),
				new CategoriaResDTO(
					movimentacaoCriada.getCategoria().getId(),
					movimentacaoCriada.getCategoria().getNome(),
					movimentacaoCriada.getCategoria().getTipo()
				)
			),
			HttpStatus.OK
		);
	}

}
