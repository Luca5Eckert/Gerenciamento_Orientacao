package service.formatacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.SessaoUsuario;

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
		IdiomaImplementacao idiomaImplementacao = SessaoUsuario.pegarIdioma();

		if( numeroSeperador != 0 ) {
			listaFormatada.append(idiomaImplementacao.pegarIdiomaDisponivel());			
		}
		for(IdiomaOrientacao idioma : listaOrientacoes) {

			if(numeroOrientacao == (numeroSeperador + 1)) {
				listaFormatada.append("\n\n" + idiomaImplementacao.pegarIdiomaIndisponivel());
				
			} 
			listaFormatada.append("\n" + numeroOrientacao + "- " + idioma.getNomePorIdioma(idiomaImplementacao.obterIdiomaOrientacao()));
			numeroOrientacao++;
		}

		
		return listaFormatada.toString();
	}

}
