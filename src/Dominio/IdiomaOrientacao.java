package Dominio;

import java.util.Arrays;
import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;



public enum IdiomaOrientacao {
    PORTUGUES("Português", "Portuguese", "Portugiesisch", "Portugués"),
    INGLES("Inglês", "English", "Englisch", "Inglés"),
    ALEMAO("Alemão", "German", "Deutsch", "Alemán"),
    ESPANHOL("Espanhol", "Spanish", "Spanisch", "Español");

    private final String idiomaPortugues;
    private final String idiomaIngles;
    private final String idiomaAlemao;
    private final String idiomaEspanhol;

    IdiomaOrientacao(String idiomaPortugues, String idiomaIngles, String idiomaAlemao, String idiomaEspanhol) {
        this.idiomaPortugues = idiomaPortugues;
        this.idiomaIngles = idiomaIngles;
        this.idiomaAlemao = idiomaAlemao;
        this.idiomaEspanhol = idiomaEspanhol;
    }

    public static List<IdiomaOrientacao> listarIdiomas() {
        return Arrays.asList(values());
    }

    public static IdiomaOrientacao pegarIdioma(int numeroIdioma) {
        IdiomaOrientacao[] valores = values();
        if (numeroIdioma >= 0 && numeroIdioma < valores.length) {
            return valores[numeroIdioma];
        }
        return PORTUGUES;
    }

    public String getNomeEmPortugues() {
        return idiomaPortugues;
    }

    public String getNomeEmIngles() {
        return idiomaIngles;
    }

    public String getNomeEmAlemao() {
        return idiomaAlemao;
    }

    public String getNomeEmEspanhol() {
        return idiomaEspanhol;
    }

    public String getNomePorIdioma(IdiomaOrientacao idioma) {
        return switch (idioma) {
            case PORTUGUES -> getNomeEmPortugues();
            case INGLES -> getNomeEmIngles();
            case ALEMAO -> getNomeEmAlemao();
            case ESPANHOL -> getNomeEmEspanhol();
        };
    }

    public static IdiomaOrientacao valueOfNome(String nome) {
        for (IdiomaOrientacao idioma : values()) {
            if (idioma.idiomaPortugues.equalsIgnoreCase(nome) ||
                idioma.idiomaIngles.equalsIgnoreCase(nome) ||
                idioma.idiomaAlemao.equalsIgnoreCase(nome) ||
                idioma.idiomaEspanhol.equalsIgnoreCase(nome)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma não reconhecido: " + nome);
    }

		public static String listaIdiomasFormatado(IdiomaImplementacao idiomaImplementacao) {
		    StringBuilder idiomasFormatados = new StringBuilder();

		    int numeroIdioma = 1;
		    for (IdiomaOrientacao idioma : values()) {
		        idiomasFormatados.append(" ")
		                         .append(numeroIdioma)
		                         .append("- ")
		                         .append(idioma.getNomePorIdioma(idiomaImplementacao.obterIdiomaOrientacao()))
		                         .append("\n");
		        numeroIdioma++;
		    }

		    return idiomasFormatados.toString();
		}

}


