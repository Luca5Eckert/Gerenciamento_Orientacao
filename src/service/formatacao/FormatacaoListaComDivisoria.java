package service.formatacao;

import java.util.List;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;

public class FormatacaoListaComDivisoria implements Formatacao {
	private int numeroDivisoria;

	public FormatacaoListaComDivisoria(int numeroDivisoria){
		this.numeroDivisoria = numeroDivisoria;
	}
	
	public String formatar(IdiomaImplementacao idiomaImplementacao, List<OrientacaoDto> listaOrientacoes) {
		StringBuilder listaFormatada = new StringBuilder();
		int numeroOrientacao = 1;

		if( numeroDivisoria != 0 ) {
			listaFormatada.append(idiomaImplementacao.pegarIdiomaDisponivel());			
		}
		for(IdiomaOrientacao idioma : listaOrientacoes) {

			if(numeroOrientacao == (numeroDivisoria + 1)) {
				listaFormatada.append("\n\n" + idiomaImplementacao.pegarIdiomaIndisponivel());
				
			} 
			listaFormatada.append("\n" + numeroOrientacao + "- " + idioma.getNomePorIdioma(idiomaImplementacao.obterIdiomaOrientacao()));
			numeroOrientacao++;
		}

		
		return listaFormatada.toString();
	}
}
