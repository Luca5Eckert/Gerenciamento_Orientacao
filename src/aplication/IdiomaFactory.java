package aplication;

import aplication.implementacoes.AlemaoImplementacao;
import aplication.implementacoes.EspanholImplementacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.implementacoes.InglesImplementacao;
import aplication.implementacoes.PortuguesImplementacao;

public class IdiomaFactory {

	public static IdiomaImplementacao pegarIdiomaImplementacao(int tipoMenu) {
		return switch (tipoMenu) {
		case 1 -> new PortuguesImplementacao();
		case 2 -> new InglesImplementacao();
		case 3 -> new AlemaoImplementacao();
		case 4 -> new EspanholImplementacao();
		default -> new PortuguesImplementacao();
		};
	}

}
