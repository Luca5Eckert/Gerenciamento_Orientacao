package service.filtros;

import java.util.Arrays;
import java.util.List;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;

public enum TipoFiltro {
    IDIOMA("Idioma", "Language", "Sprache", "Idioma", IdiomaOrientacao.class),
    TIPO("Tipo", "Type", "Typ", "Tipo", TipoOrientacao.class);

    private String nomePortugues;
    private String nomeIngles;
    private String nomeAlemao;
    private String nomeEspanhol;
    private Class<?> enumUsado;

    TipoFiltro(String nomePortugues, String nomeIngles, String nomeAlemao, String nomeEspanhol, Class<?> enumUsado) {
        this.nomePortugues = nomePortugues;
        this.nomeIngles = nomeIngles;
        this.nomeAlemao = nomeAlemao;
        this.nomeEspanhol = nomeEspanhol;
        this.enumUsado = enumUsado;
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

    public Class<?> getEnumUsado() {
        return enumUsado;
    }

    public void setEnumUsado(Class<?> enumUsado) {
        this.enumUsado = enumUsado;
    }

    @SuppressWarnings("unchecked")
    public <E extends Enum<E>> List<E> listarValoresEnum() {
        if (enumUsado.isEnum()) {
            return List.of((E[]) enumUsado.getEnumConstants());
        }
        throw new IllegalStateException("Tipo associado não é um enum: " + enumUsado);
    }
}
