package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;
import service.filtros.GerenciadorFiltrosOrientacao;

public class MenuFiltroGeral extends Menu {

	private GerenciadorFiltrosOrientacao gerenciadorFiltro;
	private SessaoUsuario sessaoUsuario;

	public MenuFiltroGeral(IdiomaImplementacao idiomaImplementacao, GerenciadorFiltrosOrientacao gerenciadorFiltro, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.gerenciadorFiltro = gerenciadorFiltro;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcao = idiomaImplementacao.mostrarMenuFiltro(input);

		processarOpcao(opcao, input, menuHistorico);
	}

	public void processarOpcao(String opcao, Scanner input, MenuHistorico menuHistorico) {
		switch (opcao.trim().toUpperCase()) {
		case "V" -> menuHistorico.voltarMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao));
		case "1" -> menuHistorico.definirProximoMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.VISUALIZAR_FILTRO, gerenciadorFiltro, idiomaImplementacao));
		case "2" -> menuHistorico.definirProximoMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.DEFINIR_FILTRO, gerenciadorFiltro, idiomaImplementacao));
		case "3" -> menuHistorico.voltarMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao));
		}
	}

}
