package service.formatacao;

import java.util.List;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;

public class FormatacaoIdioma {
	
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
