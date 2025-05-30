package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuInicial extends Menu {

	public MenuInicial(IdiomaImplementacao idiomaImplementacao) {
		super(idiomaImplementacao);
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuInicial(input);

		var proximoMenu = devolverOpcaoEscolhida(opcaoEscolhida );
	
		menuHistorico.definirProximoMenu(proximoMenu);
	}


	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch (opcao) {
		case "1" -> MenuFactory.criarMenu(TipoMenu.LOGIN, idiomaImplementacao);
		case "2" -> MenuFactory.criarMenu(TipoMenu.CADASTRO, idiomaImplementacao);
		case "3" -> MenuFactory.criarMenuAlterarSistema(TipoMenu.ALTERAR_IDIOMA, this, idiomaImplementacao);
		case "4" -> null;
		default -> MenuFactory.criarMenu(TipoMenu.INICIO, idiomaImplementacao);
		};
	}
	
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
