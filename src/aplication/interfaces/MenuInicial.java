package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuInicial implements Menu {

	public MenuInicial() {
	}

	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuInicial(input);

		return devolverOpcaoEscolhida(opcaoEscolhida, idiomaImplementacao);
	
	}


	public Menu devolverOpcaoEscolhida(String opcao, IdiomaImplementacao idiomaImplementacao) {
		return switch (opcao) {
		case "1" -> MenuFactory.criarMenuComIdioma(TipoMenu.LOGIN, idiomaImplementacao);
		case "2" -> MenuFactory.criarMenuComIdioma(TipoMenu.CADASTRO, idiomaImplementacao);
		case "3" -> MenuFactory.criarMenu(TipoMenu.FIM);
		default -> MenuFactory.criarMenu(TipoMenu.INICIO);
		};
	}

}
