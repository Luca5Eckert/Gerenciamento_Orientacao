package aplication.interfaces;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;

import java.util.Scanner;

public class MenuFalha extends Menu {
	
	private final Menu proximoMenu;
	private final String mensagemErro;
	private SessaoUsuario sessaoUsuario;
	
	public MenuFalha(IdiomaImplementacao idiomaImplementacao, Menu proximoMenu, String mensagemErro, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.proximoMenu = proximoMenu;
		this.mensagemErro = mensagemErro;
		this.sessaoUsuario = sessaoUsuario;
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
