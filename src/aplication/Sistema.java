package aplication;

import java.util.Scanner;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.implementacoes.InglesImplementacao;
import aplication.interfaces.TipoMenu;

public class Sistema {
	private static final int CONTINUAR_SISTEMA = 0;
	private static final int PARAR_SISTEMA = 0;
	private static final int ERRO_NO_SISTEMA = 0;

	private IdiomaImplementacao idiomaImplementacao;
	private MenuGerenciador gerenciadorMenu;
	private Scanner input = new Scanner(System.in);

	public Sistema() {
		this.gerenciadorMenu = new MenuGerenciador(MenuFactory.criarMenu(TipoMenu.INICIO));
		this.idiomaImplementacao = new InglesImplementacao();

	}

	public boolean iniciarSistema() {
		System.out.println("Starting the system....");
		System.out.println("Select a language: \n 1- Portuguese\n 2- English\n 3- German\n 4- Spanish");
		int opcaoIdioma = input.nextInt();
		boolean sistema = true;

		this.setIdiomaImplementacao(IdiomaFactory.pegarIdiomaImplementacao(opcaoIdioma));

		input.nextLine();

		do {
			boolean retorno = this.gerenciadorMenu.iniciarFluxoMenu(idiomaImplementacao, input);

			sistema = retorno;

		} while (sistema);

		return false;

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

	public IdiomaImplementacao getIdiomaImplementacao() {
		return idiomaImplementacao;
	}

	public void setIdiomaImplementacao(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}
}
