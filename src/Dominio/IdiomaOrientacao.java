package Dominio;

import java.util.Arrays;
import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;


public enum IdiomaOrientacao {
	PORTUGUES("Português", "Portuguese", "Portugiesisch", "Portugués"),
    INGLES("Inglês", "English", "Englisch", "Inglés"),
    ALEMAO("Alemão", "German", "Deutsch", "Alemán"),
    ESPANHOL("Espanhol", "Spanish", "Spanisch", "Español");
	
	private String idiomaPortugues;
	private String idiomaIngles;
	private String idiomaAlemao;
	private String idiomaEspanhol;
	
	IdiomaOrientacao(String idiomaPortugues, String idiomaIngles, String idiomaAlemao, String idiomaEspanhol) {
		this.idiomaPortugues = idiomaPortugues;
		this.idiomaIngles = idiomaIngles;
		this.idiomaAlemao = idiomaAlemao;
		this.idiomaEspanhol = idiomaEspanhol;
	}
	
	public static List<IdiomaOrientacao> listarIdiomas() {
        return Arrays.asList(IdiomaOrientacao.values());
    }
	
	public static IdiomaOrientacao pegarIdioma(int numeroIdioma) {
		IdiomaOrientacao[] valores = IdiomaOrientacao.values();
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
            case PORTUGUES -> idiomaPortugues;
            case INGLES -> idiomaIngles;
            case ALEMAO -> idiomaAlemao;
            case ESPANHOL -> idiomaEspanhol;
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

    
}

	

