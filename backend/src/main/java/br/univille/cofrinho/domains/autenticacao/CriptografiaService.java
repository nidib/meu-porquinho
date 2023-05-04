package br.univille.cofrinho.domains.autenticacao;

import org.mindrot.jbcrypt.BCrypt;

public class CriptografiaService {

	private CriptografiaService() {}

	public static String criptografar(String texto) {
		return BCrypt.hashpw(texto, BCrypt.gensalt());
	}

	public static boolean validar(String texto, String textoCriptografado) {
		return BCrypt.checkpw(texto, textoCriptografado);
	}

}
