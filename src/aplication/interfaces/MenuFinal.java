package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuFinal implements Menu{

	@Override
	public Menu chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		return this;
	}
	
	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
	}
	
}
