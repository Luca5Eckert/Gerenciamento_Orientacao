package service.filtros;

import java.util.List;

import Dominio.Filtro;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;

public interface FiltroOrientacao<T extends Filtro<?>> {
	
	boolean adicionarFiltro(String enumConvertido);
	
	List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> listaObjeto);
	
	List<T> pegarFiltro();

	void limparFiltros();
	
	String pegarFiltrosEmTexto(IdiomaImplementacao idiomaImplementacao);
	
	void apagarFiltro(int index);

	String pegarTodosFiltrosEmTexto(IdiomaImplementacao idiomaImplementacao);

	List<T> pegarFiltroPossiveis();
	
}
