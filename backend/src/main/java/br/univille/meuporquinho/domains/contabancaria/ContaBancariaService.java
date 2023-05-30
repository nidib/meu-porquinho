package br.univille.meuporquinho.domains.contabancaria;

import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import br.univille.meuporquinho.domains.usuario.UsuarioService;
import br.univille.meuporquinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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

	private ContaBancariaEntity obter(UUID id, UsuarioEntity usuario) {
		return this.contaBancariaRepository.obterPorIdEUsuario(id, usuario)
			.orElseThrow(() -> new RegraDeNegocioException("Conta bancária não encontrada", HttpStatus.NOT_FOUND));
	}

	private void verificaSeExiste(String titulo, UsuarioEntity usuario) {
		if (this.contaBancariaRepository.existePor(titulo, usuario)) {
			throw new RegraDeNegocioException("Conta bancária com esse nome já existe", HttpStatus.CONFLICT);
		}
	}

	private void validarVencimentoDaFatura(int diaDoVencimentoDaFatura) {
		if (diaDoVencimentoDaFatura < 1 || diaDoVencimentoDaFatura > 28) {
			throw new RegraDeNegocioException("Dia do vencimento da fatura deve ser entre 1 e 28", HttpStatus.BAD_REQUEST);
		}
	}

	private long obterSaldoEmCentavos(double saldo) {
		return (long) (saldo * 100);
	}

	public double obterSaldoEmReaisDecimal(long saldo) {
		return saldo / 100.0;
	}

	public List<ContaBancariaEntity> obterDisponiveisPor(UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);

		return this.contaBancariaRepository.obterTodasPorUsuario(usuario);
	}

	public ContaBancariaEntity criar(String titulo, double saldo, int diaDoVencimentoDaFatura, UUID usuarioId) {
		this.validarVencimentoDaFatura(diaDoVencimentoDaFatura);

		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);
		long saldoEmCentavos = this.obterSaldoEmCentavos(saldo);

		this.verificaSeExiste(titulo, usuario);

		return this.contaBancariaRepository.save(
			new ContaBancariaEntity(titulo, saldoEmCentavos, diaDoVencimentoDaFatura, usuario)
		);
	}

	public ContaBancariaEntity atualizar(UUID id, String titulo, double saldo, int diaDoVencimentoDaFatura, UUID usuarioLogadoId) {
		this.validarVencimentoDaFatura(diaDoVencimentoDaFatura);

		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioLogadoId);
		ContaBancariaEntity contaBancariaExistente = this.obter(id, usuario);
		long saldoEmCentavos = this.obterSaldoEmCentavos(saldo);
		boolean estaTentandoAlterarOTitulo = !contaBancariaExistente.getTitulo().equals(titulo);

		if (estaTentandoAlterarOTitulo) {
			this.verificaSeExiste(titulo, usuario);
		}

		ContaBancariaEntity contaBancariaAtualizada = new ContaBancariaEntity(
			contaBancariaExistente.getId(), titulo, saldoEmCentavos, diaDoVencimentoDaFatura, usuario, contaBancariaExistente.getCriadoEm()
		);

		return this.contaBancariaRepository.save(contaBancariaAtualizada);
	}

	public void remover(UUID id, UUID usuarioId) {
		UsuarioEntity usuario = this.usuarioService.obterUsuario(usuarioId);
		ContaBancariaEntity contaBancaria = this.obter(id, usuario);

		this.contaBancariaRepository.deleteById(contaBancaria.getId());
	}

}
