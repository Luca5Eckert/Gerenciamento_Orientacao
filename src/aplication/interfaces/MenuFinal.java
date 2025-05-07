package aplication.interfaces;

import java.util.Scanner;

import aplication.implementacoes.IdiomaImplementacao;

public class MenuFinal implements Menu{

	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		return this;
	}

}
