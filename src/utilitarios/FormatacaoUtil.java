package utilitarios;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FormatacaoUtil {

	public static String removerAcento(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	
	public static String removerEspacos(String texto) {
		return texto.trim();
	}
}
