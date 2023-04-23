package br.univille.cofrinho.domains.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

	private final PerfilRepository perfilRepository;

	@Autowired
	public PerfilService(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}

	public PerfilEntity create(PerfilEntity perfil) {
		return this.perfilRepository.save(perfil);
	}

}
