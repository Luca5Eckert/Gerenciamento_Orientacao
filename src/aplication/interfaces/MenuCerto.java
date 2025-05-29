package aplication.interfaces;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;

import java.util.Scanner;

public class MenuCerto extends Menu {

	private Menu proximoMenu;
	private String mensagemAcerto;
	private SessaoUsuario sessaoUsuario;

	public MenuCerto(IdiomaImplementacao idiomaImplementacao, Menu proximoMenu, String mensagemAcerto, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.proximoMenu = proximoMenu;
		this.mensagemAcerto = mensagemAcerto;
		this.sessaoUsuario = sessaoUsuario;
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
