package service.formatacao;

import java.util.List;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;

public class FormatacaoListaOrientacao implements Formatacao<OrientacaoDto> {

	@Override
	public String formatar(List<OrientacaoDto> listaParaFormatar, IdiomaImplementacao idiomaImplementacao) {
		StringBuilder formatado = new StringBuilder();
		int numeroOrientacao = 1;

		for (OrientacaoDto orientacao : listaParaFormatar) {
			formatado.append(" " + numeroOrientacao + "- " + orientacao.titulo() + "\n");
			numeroOrientacao++;
		}
		return formatado.toString();
	}

}
