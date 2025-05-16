package aplication;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.implementacoes.InglesImplementacao;
import aplication.interfaces.MenuIniciarSistema;
import aplication.interfaces.TipoMenu;

public class Sistema {
	private static final int CONTINUAR_SISTEMA = 0;
	private static final int PARAR_SISTEMA = 0;
	private static final int ERRO_NO_SISTEMA = 0;

	private MenuGerenciador gerenciadorMenu;
	private Scanner input = new Scanner(System.in);

	public Sistema() {
		IdiomaImplementacao idioma = new InglesImplementacao();
		this.gerenciadorMenu = new MenuGerenciador(MenuFactory.criarMenu(TipoMenu.FIM, idioma));
	}

	public void iniciarSistema() {
		System.out.println("Starting the system....");
		iniciarSistemaMenu();
		
		boolean estaFuncionando = true;

		do {
			estaFuncionando = this.gerenciadorMenu.iniciarFluxoMenu(input);

		} while (estaFuncionando);

	}

	private void iniciarSistemaMenu() {
		this.gerenciadorMenu.setMenu(new MenuIniciarSistema());
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
