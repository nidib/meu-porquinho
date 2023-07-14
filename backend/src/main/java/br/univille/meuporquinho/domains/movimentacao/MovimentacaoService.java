package br.univille.meuporquinho.domains.movimentacao;

import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import br.univille.meuporquinho.domains.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class MovimentacaoService {

	private final MovimentacaoRepository movimentacaoRepository;
	private final UsuarioService usuarioService;

	@Autowired
	public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, UsuarioService usuarioService) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.usuarioService = usuarioService;
	}

	public List<MovimentacaoEntity> obterTudo(UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);

		return this.movimentacaoRepository.obterTudoOrdenadoPorData(usuario);
	}
}
