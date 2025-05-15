package service.formatacao;

public interface Formatacao {

    public String formatar(List<?> listaParaFormatar, IdiomaImplementacao idiomaImplementacao);

    public void definirCaracteristica(Object objeto);
}
