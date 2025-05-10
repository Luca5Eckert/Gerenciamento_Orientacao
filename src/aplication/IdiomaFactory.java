package aplication;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.AlemaoImplementacao;
import aplication.implementacoes.EspanholImplementacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.implementacoes.InglesImplementacao;
import aplication.implementacoes.PortuguesImplementacao;

public class IdiomaFactory {

	public static IdiomaImplementacao pegarIdiomaImplementacao(IdiomaOrientacao idiomaOrientacao) {
		return switch (idiomaOrientacao) {
		case PORTUGUES -> new PortuguesImplementacao();
		case INGLES -> new InglesImplementacao();
		case ALEMAO -> new AlemaoImplementacao();
		case ESPANHOL -> new EspanholImplementacao();
		default -> new PortuguesImplementacao();
		};
	}

}
