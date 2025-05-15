package service.formatacao;

import java.util.List;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.SessaoUsuario;

public class FormatacaoListaComDivisoria implements Formatacao<IdiomaOrientacao> {
	private int numeroDivisoria;

	public FormatacaoListaComDivisoria(int numeroDivisoria){
		this.numeroDivisoria = numeroDivisoria;
	}

	@Override
	public String formatar(List<IdiomaOrientacao> listaParaFormatar, IdiomaImplementacao idiomaImplementacao) {
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
