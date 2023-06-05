package br.univille.meuporquinho.util;
public class Sanitizacao {
	public static String verificaDados(String emailOuLogin) {
		String lowerEmailOuLogin = emailOuLogin.toLowerCase().replaceAll("\\s+", "");
		return lowerEmailOuLogin;
	}
}
