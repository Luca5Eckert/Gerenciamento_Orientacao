package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;

public class MenuGeral extends Menu {

	private SessaoUsuario sessaoUsuario;
	
	public MenuGeral(IdiomaImplementacao idiomaImplementacao, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcao = idiomaImplementacao.mostrarMenuGeral(input);

		processarOpcao(opcao, menuHistorico);

	}

	public void processarOpcao(String opcao, MenuHistorico menuHistorico) {
		switch (opcao) {
		case "0" -> menuHistorico
				.definirProximoMenu(MenuFactory.criarMenuComIdioma(TipoMenu.ADICAO_ORIENTACAO, idiomaImplementacao));
		case "1" -> menuHistorico
				.definirProximoMenu(MenuFactory.criarMenuComIdioma(TipoMenu.EXIBIR_ORIENTACOES, idiomaImplementacao));
		case "2" ->
			menuHistorico.definirProximoMenu(MenuFactory.criarMenuComIdioma(TipoMenu.INICIO, idiomaImplementacao));
		case "3" -> menuHistorico.definirProximoMenu(null);
		case "4" -> menuHistorico.definirProximoMenu(
				MenuFactory.criarMenuAlterarSistema(TipoMenu.ALTERAR_IDIOMA, this, idiomaImplementacao));
		}
		;
	}


}
