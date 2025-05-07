package aplication;

import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.Menu;
import aplication.interfaces.MenuFinal;

import java.util.Scanner;

public class MenuGerenciador {
	private Menu menu;
	
	public MenuGerenciador(Menu menu) {
		this.menu = menu;
	}
	
	public boolean iniciarFluxoMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		Menu novoMenu = this.iniciarMenu(idiomaImplementacao, input);
		
		this.definirNovoMenu(novoMenu);
		
		return definirRetorno(novoMenu);
		
	}
	
	public Menu iniciarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		return menu.chamarMenu(idiomaImplementacao, input);
	}
	
	
	public boolean definirRetorno (Menu menu) {
		if ( menu instanceof MenuFinal) {
			return false;
		} else  {
			return true;
		}
	}
	
	public void definirNovoMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
