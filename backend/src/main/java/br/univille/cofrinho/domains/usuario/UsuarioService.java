package br.univille.cofrinho.domains.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository){this.usuarioRepository = usuarioRepository;}

}
