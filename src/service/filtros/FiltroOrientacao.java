package service.filtros;

import java.util.List;

import dtos.OrientacaoDto;

public interface FiltroOrientacao<T extends Object> {
	
	void adicionarFiltro(T filtro);
	
	List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> listaObjeto);
	
	List<T> pegarFiltro();
}
