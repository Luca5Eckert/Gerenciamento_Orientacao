package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuGeral implements Menu {
	
	private IdiomaImplementacao idiomaImplementacao;
	
	public MenuGeral(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public Menu chamarMenu( Scanner input) {
		String opcao = idiomaImplementacao.mostrarMenuGeral(input);
		
		Menu menu =devolverOpcaoEscolhida(opcao);
		
		return menu;
	}

	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch (opcao) {
		case "0" -> MenuFactory.criarMenuComIdioma(TipoMenu.ADICAO_ORIENTACAO);
		case "1" -> MenuFactory.criarMenuComIdioma(TipoMenu.EXIBIR_ORIENTACOES);
		case "2" -> MenuFactory.criarMenu(TipoMenu.INICIO);
		case "3" -> MenuFactory.criarMenu(TipoMenu.FIM);
		case "4" -> MenuFactory.criarMenuAlterarSistema(TipoMenu.ALTERAR_IDIOMA, this );
		default -> this;
		};
	}
	
	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
