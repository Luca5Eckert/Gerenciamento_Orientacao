package service.formatacao;

import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.filtros.TipoFiltro;

public class FormatacaoNumerarLista{

	public String formatarOrientacoesPorTitulo(List<OrientacaoDto> listaParaFormatar, IdiomaImplementacao idiomaImplementacao) {
		StringBuilder formatado = new StringBuilder();
		int numeroOrientacao = 1;

		for (OrientacaoDto orientacao : listaParaFormatar) {
			formatado.append(" " + numeroOrientacao + "- " + orientacao.titulo() + "\n");
			numeroOrientacao++;
		}
		return formatado.toString();
	}

	public String formatarTiposDeFiltro(List<TipoFiltro> listaParaFormatar, IdiomaImplementacao idiomaImplementacao) {
		StringBuilder formatado = new StringBuilder();
		int numeroTipoFiltro = 1;
		
		
		for(TipoFiltro tipoFiltro : listaParaFormatar) {
			formatado.append(" " + numeroTipoFiltro + "- " + tipoFiltro.pegarNome(idiomaImplementacao.obterIdiomaOrientacao()) + "\n");
			numeroTipoFiltro++;
		}
		return formatado.toString();
	}
	
	public String formatarString(List<String> listaFiltros) {
		StringBuilder formatado = new StringBuilder();
		int numeroFiltro = 1;

		for (String filtro : listaFiltros) {
			formatado.append(" " + numeroFiltro + "- " + filtro + "\n");
			numeroFiltro++;
		}
		return formatado.toString();
	}
}
