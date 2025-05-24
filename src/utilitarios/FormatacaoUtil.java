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

	public static String enquadrarTextoNoMenu(String conteudo, int larguraLinha, int tamanhoEspacamento) {
		String espacamento = criarEspacamento(tamanhoEspacamento);
		StringBuilder conteudoEnquadro = new StringBuilder();
		String[] palavras = conteudo.split(" ");
		int comprimentoAtual = 0;

		for (String palavra : palavras) {

			if (comprimentoAtual + palavra.length() > larguraLinha) {
				conteudoEnquadro.append("\ne" + espacamento);
				comprimentoAtual = 0;
			}

			if (comprimentoAtual != 0) {
				conteudoEnquadro.append(" ");
				comprimentoAtual += 1;
			}

			conteudoEnquadro.append(palavra);
			comprimentoAtual += palavra.length();
		}

		return conteudoEnquadro.toString();
	}

	private static String criarEspacamento(int tamanhoEspacamento) {
		StringBuilder espacamento = new StringBuilder();
		
		for(int i=1; i <= tamanhoEspacamento; i++) {
			espacamento.append(" ");
		}
		
		return espacamento.toString();
	}

}
