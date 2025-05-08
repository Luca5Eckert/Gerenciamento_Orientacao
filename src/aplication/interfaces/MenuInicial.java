package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuInicial implements Menu {
	private IdiomaImplementacao idiomaImplementacao;

	public MenuInicial(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	public Menu chamarMenu(Scanner input) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuInicial(input);

		return devolverOpcaoEscolhida(opcaoEscolhida );
	
	}


	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch (opcao) {
		case "1" -> MenuFactory.criarMenuComIdioma(TipoMenu.LOGIN);
		case "2" -> MenuFactory.criarMenuComIdioma(TipoMenu.CADASTRO);
		case "3" -> MenuFactory.criarMenu(TipoMenu.FIM);
		default -> MenuFactory.criarMenu(TipoMenu.INICIO);
		};
	}

}
