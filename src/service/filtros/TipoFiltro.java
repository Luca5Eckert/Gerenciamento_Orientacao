package service.filtros;

import java.util.Arrays;
import java.util.List;

import Dominio.Filtro;
import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;

public enum TipoFiltro {
	IDIOMA("Idioma", "Language", "Sprache", "Idioma", IdiomaOrientacao.PORTUGUES),
    TIPO("Tipo", "Type", "Typ", "Tipo", TipoOrientacao.MANUAL_CONDUTA_OPERACOES);

	
    private String nomePortugues;
    private String nomeIngles;
    private String nomeAlemao;
    private String nomeEspanhol;
    private Filtro<?> enumFiltro;

    TipoFiltro(String nomePortugues, String nomeIngles, String nomeAlemao, String nomeEspanhol, Filtro<?> enumFiltro) {
        this.nomePortugues = nomePortugues;
        this.nomeIngles = nomeIngles;
        this.nomeAlemao = nomeAlemao;
        this.nomeEspanhol = nomeEspanhol;
        this.enumFiltro = enumFiltro;
    }

    public String pegarNome(IdiomaOrientacao idiomaOrientacao) {
        return switch (idiomaOrientacao) {
            case INGLES -> getNomeIngles();
            case PORTUGUES -> getNomePortugues();
            case ALEMAO -> getNomeAlemao();
            case ESPANHOL -> getNomeEspanhol();
            default -> getNomeIngles();
        };
    }

    public static List<TipoFiltro> listarTipoFiltros() {
        return Arrays.asList(values());
    }

    public static TipoFiltro pegarTipoFiltroPorIndex(int index) {
        var listaFiltros = listarTipoFiltros();
        return listaFiltros.get(index - 1);
    }

    public String getNomePortugues() {
        return nomePortugues;
    }

    public String getNomeIngles() {
        return nomeIngles;
    }

    public String getNomeAlemao() {
        return nomeAlemao;
    }

    public String getNomeEspanhol() {
        return nomeEspanhol;
    }

	public Filtro<?> getEnumFiltro() {
		return enumFiltro;
	}

	public void setEnumFiltro(Filtro<?> enumFiltro) {
		this.enumFiltro = enumFiltro;
	}



    
}
