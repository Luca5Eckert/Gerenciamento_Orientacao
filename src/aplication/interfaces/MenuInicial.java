package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuInicial implements Menu {
	private IdiomaImplementacao idiomaImplementacao;

	public MenuInicial(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public Menu chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuInicial(input);

		return devolverOpcaoEscolhida(opcaoEscolhida );
	
	}


	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch (opcao) {
		case "1" -> MenuFactory.criarMenuComIdioma(TipoMenu.LOGIN, idiomaImplementacao);
		case "2" -> MenuFactory.criarMenuComIdioma(TipoMenu.CADASTRO, idiomaImplementacao);
		case "3" -> MenuFactory.criarMenuAlterarSistema(TipoMenu.ALTERAR_IDIOMA, this, idiomaImplementacao);
		case "4" -> MenuFactory.criarMenu(TipoMenu.FIM, idiomaImplementacao);
		default -> MenuFactory.criarMenu(TipoMenu.INICIO, idiomaImplementacao);
		};
	}
	
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
