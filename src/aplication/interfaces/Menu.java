package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public interface Menu {

	Menu chamarMenu(Scanner input, MenuHistorico menuHistorico);
	
	void mudarIdioma(IdiomaImplementacao idiomaImplementacao);


}
