package aplication;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.implementacoes.InglesImplementacao;
import aplication.interfaces.TipoMenu;
import service.SessaoUsuario;

public class Sistema {
	private static final int CONTINUAR_SISTEMA = 0;
	private static final int PARAR_SISTEMA = 0;
	private static final int ERRO_NO_SISTEMA = 0;

	private MenuGerenciador gerenciadorMenu;
	private Scanner input = new Scanner(System.in);

	public Sistema() {
		this.gerenciadorMenu = new MenuGerenciador(MenuFactory.criarMenu(TipoMenu.FIM));
	}

	public boolean iniciarSistema() {
		System.out.println("Starting the system....");
		System.out.println("Select a language:");
		System.out.println(IdiomaOrientacao.listaIdiomasFormatado(new InglesImplementacao()));
		
		int opcaoIdioma = input.nextInt();
		boolean sistema = true;

		IdiomaImplementacao idiomaImplementacao = IdiomaFactory.pegarIdiomaImplementacao(IdiomaOrientacao.pegarIdioma(opcaoIdioma-1));
		iniciarGerenciador(idiomaImplementacao);
		
		input.nextLine();

		do {
			boolean retorno = this.gerenciadorMenu.iniciarFluxoMenu(input);

			sistema = retorno;

		} while (sistema);

		return false;

	}

	@SuppressWarnings("unused")
	private void iniciarGerenciador(IdiomaImplementacao idionaImplementacao) {
		SessaoUsuario.definirIdioma(idionaImplementacao);
		this.gerenciadorMenu.setMenu(MenuFactory.criarMenu(TipoMenu.INICIO));
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
