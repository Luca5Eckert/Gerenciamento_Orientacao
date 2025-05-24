package aplication.interfaces;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import java.util.Scanner;

public class MenuCerto extends Menu {

	private Menu proximoMenu;
	private String mensagemAcerto;

	public MenuCerto(IdiomaImplementacao idiomaImplementacao, Menu proximoMenu, String mensagemAcerto) {
		super(idiomaImplementacao);
		this.proximoMenu = proximoMenu;
		this.mensagemAcerto = mensagemAcerto;
	}

	@Override
	public void chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		idiomaImplementacao.mostrarMenuAcerto(input, mensagemAcerto);

		irParaProximoMenu(menuHistorico); 
			
	}

	public void irParaProximoMenu(MenuHistorico menuHistorico) {
		menuHistorico.voltarMenu();
		menuHistorico.definirProximoMenu(proximoMenu);
	}
	


}
