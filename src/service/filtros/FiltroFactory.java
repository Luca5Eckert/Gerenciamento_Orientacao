package service.filtros;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;

public class FiltroFactory {

	public static FiltroOrientacao<IdiomaOrientacao> criarFiltroOrientacaoIdioma() {
	    return new FiltroOrientacaoIdioma();
	}
	
	public static FiltroOrientacao<TipoOrientacao> criarFiltroOrientacaoTipo(){
		return new FiltroOrientacaoTipo();
	}

	
}
