package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public abstract class Menu {
	protected IdiomaImplementacao idiomaImplementacao;
	
	public Menu(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	public abstract void chamarMenu(Scanner input, MenuHistorico menuHistorico);
	
}
