package br.univille.meuporquinho.domains.contabancaria;

import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import br.univille.meuporquinho.domains.usuario.UsuarioService;
import br.univille.meuporquinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContaBancariaService {

	private final ContaBancariaRepository contaBancariaRepository;

	private final UsuarioService usuarioService;

	@Autowired
	public ContaBancariaService(ContaBancariaRepository contaBancariaRepository, UsuarioService usuarioService) {
		this.contaBancariaRepository = contaBancariaRepository;
		this.usuarioService = usuarioService;
	}

	public ContaBancariaEntity criar(String titulo, double saldo, int diaDoVencimentoDaFatura, UUID usuarioId) {
		if (diaDoVencimentoDaFatura < 1 || diaDoVencimentoDaFatura > 28) {
			throw new RegraDeNegocioException("Dia do vencimento da fatura deve ser entre 1 e 28", HttpStatus.BAD_REQUEST);
		}

		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);
		Optional<ContaBancariaEntity> contaBancariaExistente = this.contaBancariaRepository.obterPorTituloEUsuario(titulo, usuario);
		long saldoEmCentavos = (long) (saldo * 100);

		if (contaBancariaExistente.isPresent()) {
			throw new RegraDeNegocioException("Conta bancária com esse nome já existe", HttpStatus.CONFLICT);
		}

		return this.contaBancariaRepository.save(
			new ContaBancariaEntity(titulo, saldoEmCentavos, diaDoVencimentoDaFatura, usuario)
		);
	}

}
