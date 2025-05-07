package Dominio;

import java.util.Arrays;
import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;


public enum IdiomaOrientacao {
	PORTUGUES, INGLES, ALEMAO, ESPANHOL;

	
	
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

	
}
