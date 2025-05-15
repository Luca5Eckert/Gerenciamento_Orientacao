package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuGeral implements Menu {
	
	private IdiomaImplementacao idiomaImplementacao;
	
	public MenuGeral(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public Menu chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcao = idiomaImplementacao.mostrarMenuGeral(input);
		
		Menu menu =devolverOpcaoEscolhida(opcao);
		
		return menu;
	}

	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch (opcao) {
		case "0" -> MenuFactory.criarMenuComIdioma(TipoMenu.ADICAO_ORIENTACAO, idiomaImplementacao);
		case "1" -> MenuFactory.criarMenuComIdioma(TipoMenu.EXIBIR_ORIENTACOES, idiomaImplementacao);
		case "2" -> MenuFactory.criarMenuComIdioma(TipoMenu.INICIO, idiomaImplementacao);
		case "3" -> MenuFactory.criarMenu(TipoMenu.FIM, idiomaImplementacao);
		case "4" -> MenuFactory.criarMenuAlterarSistema(TipoMenu.ALTERAR_IDIOMA, this, idiomaImplementacao );
		default -> this;
		};
	}
	
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
