package aplication.interfaces;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

import java.util.Scanner;

public class MenuFalha extends Menu {
	
	private final Menu proximoMenu;
	private final String mensagemErro;
	
	public MenuFalha(IdiomaImplementacao idiomaImplementacao, Menu proximoMenu, String mensagemErro) {
		super(idiomaImplementacao);
		this.proximoMenu = proximoMenu;
		this.mensagemErro = mensagemErro;
	}
	
	
	@Override
	public void chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		idiomaImplementacao.mostrarMenuErro(input, mensagemErro);
		irParaProximoMenu(menuHistorico);
	}


	public void irParaProximoMenu(MenuHistorico menuHistorico) {
		menuHistorico.voltarMenu();
		menuHistorico.definirProximoMenu(proximoMenu);
	}

}
