package br.univille.meuporquinho.utils;

public class SanitizacaoDeTextoUtils {

	public static String lowerCaseERemoveEspacos(String texto) {
		return texto.toLowerCase().replaceAll("\\s+", "");
	}

}
