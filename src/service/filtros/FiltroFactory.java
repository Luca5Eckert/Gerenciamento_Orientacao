package service.filtros;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;

public class FiltroFactory {

	public static FiltroOrientacao pegarFiltroIdioma(IdiomaOrientacao idiomaOrientacao) {
		return new FiltroOrientacaoIdioma(idiomaOrientacao);
	}
	
	public static FiltroOrientacao pegarFiltroTipo(TipoOrientacao tipoOrientacao) {
		return new FiltroOrientacaoTipo(tipoOrientacao);
	}
	
}
