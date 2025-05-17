package service.formatacao;

import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.TipoMenu;
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
	
}
