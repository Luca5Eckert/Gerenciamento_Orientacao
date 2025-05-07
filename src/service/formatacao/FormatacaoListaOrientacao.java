package service.formatacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Dominio.IdiomaOrientacao;
import dtos.OrientacaoDto;

public class FormatacaoListaOrientacao {

	public String formatarListaOrientacoes(List<OrientacaoDto> lista) {
		StringBuilder formatado = new StringBuilder();
		int numeroOrientacao = 1;

		for (OrientacaoDto orientacao : lista) {
			formatado.append(" " + numeroOrientacao + "- " + orientacao.titulo() + "\n");
			numeroOrientacao++;
		}
		return formatado.toString();
	}

	public String formatarOrientacoesIdiomas(List<IdiomaOrientacao> listaOrientacoes, int numeroSeperador) {
		StringBuilder listaFormatada = new StringBuilder();
		int numeroOrientacao = 1;

		listaFormatada.append("Disponíveis:");
		for(IdiomaOrientacao idioma : listaOrientacoes) {
			if(numeroOrientacao == (numeroSeperador + 1)) {
				listaFormatada.append("Indisponíveis:");
				
			}
			listaFormatada.append("\n " + numeroOrientacao + "- " + idioma.name());
			
		}

		
		return listaFormatada.toString();
	}

}
