package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public interface Menu {

	void chamarMenu(Scanner input, MenuHistorico menuHistorico);
	
	void trocarIdioma(IdiomaImplementacao idiomaImplementacao);
	
}
