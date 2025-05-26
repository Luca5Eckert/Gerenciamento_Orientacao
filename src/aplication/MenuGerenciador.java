package aplication;

import aplication.interfaces.Menu;

import java.util.Scanner;

public class MenuGerenciador {
	private Menu menu;
	private MenuHistorico menuHistorico;


	public MenuGerenciador(Menu menu) {
		this.menu = menu;
		this.menuHistorico = new MenuHistorico(menu);
	}

	public boolean iniciarFluxoMenu(Scanner input) {
		this.menu.chamarMenu(input, menuHistorico);
		this.definirNovoMenu();
		menuHistorico.mostrarHistorico();
		return definirRetorno(input);
	}

	public boolean definirRetorno(Scanner input) {
		return menuHistorico.pegarMenuAtual() != null;
	}

	public void definirNovoMenu() {
		this.menu = menuHistorico.pegarMenuAtual();
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
