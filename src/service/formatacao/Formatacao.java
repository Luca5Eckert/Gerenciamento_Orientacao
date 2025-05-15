package service.formatacao;

import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;

public interface Formatacao<T extends Object> {

    public String formatar(List<T> listaOrdenada, IdiomaImplementacao idiomaImplementacao);
}
