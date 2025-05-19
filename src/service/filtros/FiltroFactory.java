package service.filtros;

import Dominio.Filtro;
import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;

public class FiltroFactory {

	public static Filtro<? extends Enum<?>> obterEnumFiltroPorTipo(TipoFiltro tipoFiltro) {
		return switch (tipoFiltro) {
			case IDIOMA -> IdiomaOrientacao.PORTUGUES;
			case TIPO -> TipoOrientacao.MANUAL_OPERACAO;
		};
	}

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> FiltroOrientacao<T> criarFiltro(TipoFiltro tipoFiltro) {
		switch (tipoFiltro) {
			case IDIOMA:
				return (FiltroOrientacao<T>) new FiltroOrientacaoIdioma(); 
			case TIPO:
				return (FiltroOrientacao<T>) new FiltroOrientacaoTipo();
			default:
				throw new IllegalArgumentException("Filtro não suportado: " + tipoFiltro);
			}
		}

	
}
