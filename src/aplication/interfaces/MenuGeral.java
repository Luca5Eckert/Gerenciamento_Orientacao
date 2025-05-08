package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuGeral implements Menu {
	
	private final IdiomaImplementacao idiomaImplementacao;
	
	public MenuGeral(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public Menu chamarMenu( Scanner input) {
		String opcao = idiomaImplementacao.mostrarMenuGeral(input);
		
		System.out.println(opcao); 
		Menu menu =devolverOpcaoEscolhida(opcao, idiomaImplementacao);
		System.out.println(menu);
		
		return menu;
	}

	public Menu devolverOpcaoEscolhida(String opcao, IdiomaImplementacao idiomaImplementacao) {
		return switch (opcao) {
		case "0" -> MenuFactory.criarMenuComIdioma(TipoMenu.ADICAO_ORIENTACAO);
		case "1" -> MenuFactory.criarMenuComIdioma(TipoMenu.EXIBIR_ORIENTACOES);
		case "2" -> MenuFactory.criarMenu(TipoMenu.INICIO);
		case "3" -> MenuFactory.criarMenu(TipoMenu.FIM);
		default -> this;
		};
	}

}
