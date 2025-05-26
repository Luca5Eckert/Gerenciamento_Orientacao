package aplication;

import java.util.Scanner;

import aplication.implementacoes.InglesImplementacao;
import aplication.interfaces.MenuIniciarSistema;

public class Sistema {

	private MenuGerenciador gerenciadorMenu;
	private Scanner input = new Scanner(System.in);

	public void iniciarSistema() {
		System.out.println("Starting the system....");
		iniciarSistemaMenu();
		
		boolean estaFuncionando = true;

		do {
			estaFuncionando = this.gerenciadorMenu.iniciarFluxoMenu(input);

		} while (estaFuncionando);

	}

	private void iniciarSistemaMenu() {
		this.gerenciadorMenu = new MenuGerenciador(new MenuIniciarSistema(new InglesImplementacao()));
	}

	public MenuGerenciador getMenu() {
		return gerenciadorMenu;
	}

	public void setMenu(MenuGerenciador menu) {
		this.gerenciadorMenu = menu;
	}

	public void close() {
		this.input.close();
	}

}
