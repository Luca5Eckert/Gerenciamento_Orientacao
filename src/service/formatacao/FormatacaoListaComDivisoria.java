package service.formatacao;

import java.util.List;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.SessaoUsuario;

public class FormatacaoListaComDivisoria {

	public FormatacaoListaComDivisoria() {
	}

	public String formatar(List<IdiomaOrientacao> listaParaFormatar,int numeroDivisoria, IdiomaImplementacao idiomaImplementacao) {
		StringBuilder listaFormatada = new StringBuilder();
		int numeroOrientacao = 1;

		if( numeroDivisoria != 0 ) {
			listaFormatada.append(idiomaImplementacao.pegarIdiomaDisponivel());			
		}
		for(IdiomaOrientacao idioma : listaParaFormatar) {

			if(numeroOrientacao == (numeroDivisoria + 1)) {
				listaFormatada.append("\n\n" + idiomaImplementacao.pegarIdiomaIndisponivel());
				
			} 
			listaFormatada.append("\n" + numeroOrientacao + "- " + idioma.getNomePorIdioma(idiomaImplementacao.obterIdiomaOrientacao()));
			numeroOrientacao++;
		}

		
		return listaFormatada.toString();
	}
	

}
