package aplication.interfaces;

import java.util.Scanner;

import aplication.implementacoes.IdiomaImplementacao;

public interface Menu {

	Menu chamarMenu(Scanner input);
	
	void mudarIdioma(IdiomaImplementacao idiomaImplementacao);


}
