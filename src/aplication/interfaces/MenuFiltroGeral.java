package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.filtros.FiltroOrientacao;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.filtros.TipoFiltro;
import service.formatacao.FormatacaoNumerarLista;

public class MenuFiltroGeral implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private GerenciadorFiltrosOrientacao gerenciadorFiltro;

	public MenuFiltroGeral(IdiomaImplementacao idiomaImplementacao, GerenciadorFiltrosOrientacao gerenciadorFiltro) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.gerenciadorFiltro = gerenciadorFiltro;
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
		case "3" -> menuHistorico.definirProximoMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.VISUALIZAR_FILTRO, gerenciadorFiltro, idiomaImplementacao));
		}
	}

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;

	}
}
