package br.univille.meuporquinho.domains.perfil;

import br.univille.meuporquinho.domains.autenticacao.CriptografiaService;
import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import br.univille.meuporquinho.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PerfilService {

	private PerfilRepository perfilRepository;

	@Autowired
	public PerfilService(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}

	public PerfilEntity criar(String nomeCompleto, LocalDate dataDeNascimento) {

		PerfilEntity perfil = new PerfilEntity(nomeCompleto, dataDeNascimento);
		return this.perfilRepository.save(perfil);
	}

	public PerfilEntity atualizar(UUID usuarioId, String nomeCompleto, LocalDate dataDeNascimento, String apelido) {
		PerfilEntity perfilExistente = this.perfilRepository.findByUsuarioId(usuarioId)
			.orElseThrow(() -> new RegraDeNegocioException("Perfil não encontrado", HttpStatus.NOT_FOUND));

		return this.perfilRepository.save(
			new PerfilEntity(
				perfilExistente.getId(),
				nomeCompleto,
				dataDeNascimento,
				apelido,
				perfilExistente.getCriadoEm()
			)
		);
	}

	public void deletetarPorId(UUID id) {
		this.perfilRepository.deleteById(id);
	}


}
