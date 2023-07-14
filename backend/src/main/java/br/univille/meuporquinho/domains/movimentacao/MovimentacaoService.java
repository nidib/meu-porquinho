package br.univille.meuporquinho.domains.movimentacao;

import br.univille.meuporquinho.domains.categoria.CategoriaEntity;
import br.univille.meuporquinho.domains.categoria.CategoriaService;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaEntity;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaService;
import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import br.univille.meuporquinho.domains.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MovimentacaoService {

	private final MovimentacaoRepository movimentacaoRepository;
	private final UsuarioService usuarioService;
	private final ContaBancariaService contaBancariaService;
	private final CategoriaService categoriaService;

	@Autowired
	public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, UsuarioService usuarioService, ContaBancariaService contaBancariaService, CategoriaService categoriaService) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.usuarioService = usuarioService;
		this.contaBancariaService = contaBancariaService;
		this.categoriaService = categoriaService;
	}

	public List<MovimentacaoEntity> obterTudo(UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);

		return this.movimentacaoRepository.obterTudoOrdenadoPorData(usuario);
	}

	public MovimentacaoEntity criar(String nome, long valor, LocalDate data, LocalDate dataDeConclusao, UUID contaBancariaId, UUID categoriaId, UUID usuarioId) {
		ContaBancariaEntity contaBancaria = this.contaBancariaService.obterPorIdEUsuario(contaBancariaId, usuarioId);
		CategoriaEntity categoria = this.categoriaService.obterDisponiveisPor(usuarioId).stream().filter(c -> c.getId().equals(categoriaId)).findFirst().orElseThrow();
		MovimentacaoEntity movimentacao = new MovimentacaoEntity(
			nome,
			this.contaBancariaService.obterSaldoEmCentavos(valor),
			data,
			dataDeConclusao,
			contaBancaria,
			categoria
		);

		return this.movimentacaoRepository.save(movimentacao);
	}
}
