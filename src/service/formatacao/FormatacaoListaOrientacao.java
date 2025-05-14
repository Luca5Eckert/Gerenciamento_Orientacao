package service.formatacao;

import java.util.List;
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

}
