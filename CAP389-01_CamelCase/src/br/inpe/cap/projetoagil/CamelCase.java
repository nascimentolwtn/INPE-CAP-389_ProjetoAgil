package br.inpe.cap.projetoagil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CamelCase {

	private static final String REGEX = 
			"([^_A-Z])([A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|(?<=[a-z])(?=[0-9])";
	private static final String SUBLINHADO = "_";
	private static final String REGEX_SUBLINHADO = "$1" + SUBLINHADO + "$2";
	
	private static final Pattern PATTERN_NUMEROS = Pattern.compile("^(\\d+.*)");
	private static final Pattern PATTERN_CARACTERES_ESPECIAIS = Pattern.compile("\\W");
	
	public static List<String> converterCamelCase(String original) {
		checkValidString(original);
		List<String> listaStrings = new ArrayList<>();
		String sublinhado = original.replaceAll(REGEX, REGEX_SUBLINHADO);
		String[] separadas = sublinhado.split(SUBLINHADO);
		for (String parte : separadas) {
			if(!isSigla(parte)) {
				parte = parte.toLowerCase();
			}
			listaStrings.add(parte);
		}
		return listaStrings ;
	}

	private static boolean isSigla(String parte) {
		return parte.length() > 1 && parte.equals(parte.toUpperCase());
	}

	private static void checkValidString(String original) {
		if(PATTERN_NUMEROS.matcher(original).matches()) {
			throw new IllegalArgumentException("Palavra não deve começar com números.");
		}
		if(PATTERN_CARACTERES_ESPECIAIS.matcher(original).find()) {
			throw new IllegalArgumentException(
					"Caracteres especiais não são permitidos, somente letras e números.");
		}
	}
}

/*

	private static final Pattern PATTERN_NUMEROS = Pattern.compile("^(\\d+.*)");
	private static final Pattern PATTERN_CARACTERES_ESPECIAIS = Pattern.compile("\\p{Punct}");

	private static final Pattern PATTERN_CARACTERES_ESPECIAIS = Pattern.compile("[^a-zA-Z0-9]|\\W+.*");

	private static final String REGEX = "([^_A-Z])([A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|(?<=[a-z])(?=[0-9])";

	private static final String REGEX = "([^_A-Z])([A-Z])|(?<=[A-Z])(?=[A-Z][a-z])";
	private static final String REGEX = "([^_A-Z])([A-Z])";
	private static final String REGEX = "([a-z])([A-Z]+)";
	private static final String REGEX = "(.)(\\p{Upper})";
  	private static final String REGEX = 
		String.format("%s|%s|%s",
		"(?<=[A-Z])(?=[A-Z][a-z])",
		"(?<=[^A-Z])(?=[A-Z])",
		"(?<=[A-Za-z])(?=[^A-Za-z])"
		);
*/