package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.filtros.TipoFiltro;
import service.formatacao.FormatacaoNumerarLista;

public class MenuDefinirFiltro implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private GerenciadorFiltrosOrientacao gerenciadorFiltrosOrientacao;
	private FormatacaoNumerarLista formatacaoLista;

	public MenuDefinirFiltro(IdiomaImplementacao idiomaImplementacao,
			GerenciadorFiltrosOrientacao gerenciadorFiltrosOrientacao, FormatacaoNumerarLista formatacaoLista) {
		super();
		this.idiomaImplementacao = idiomaImplementacao;
		this.gerenciadorFiltrosOrientacao = gerenciadorFiltrosOrientacao;
		this.formatacaoLista = formatacaoLista;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		List<TipoFiltro> tiposExistentes = TipoFiltro.listarTipoFiltros();

		String tipoOrientacoesDisponiveis = formatacaoLista.formatarTiposDeFiltro(tiposExistentes, idiomaImplementacao);

		String opcao = idiomaImplementacao.mostrarMenuVisualizarTiposFiltros(input, tipoOrientacoesDisponiveis);

		switch (opcao.trim().toUpperCase()) {
		case "V" -> menuHistorico.voltarMenu(menuHistorico.voltarMenu(MenuFactory
				.criarMenuComFiltros(TipoMenu.FILTRO_GERAL, gerenciadorFiltrosOrientacao, idiomaImplementacao)));
		default -> visualizarFiltros(opcao, input, menuHistorico);
		}

	}

	private void visualizarFiltros(String opcao, Scanner input, MenuHistorico menuHistorico) {
		try {
			int indexTipoFiltro = Integer.parseInt(opcao);
			var tipoFiltro = TipoFiltro.pegarTipoFiltroPorIndex(indexTipoFiltro);

			var listaFiltros = gerenciadorFiltrosOrientacao.pegarFiltrosPossiveisEmTexto(tipoFiltro,
					idiomaImplementacao);

			String opcaoEscolhida = idiomaImplementacao.mostrarMenuVisualizarFiltros(input, listaFiltros,
					tipoFiltro.pegarNome(idiomaImplementacao.obterIdiomaOrientacao()));

			definirFiltro(input, tipoFiltro, opcaoEscolhida, menuHistorico);

		} catch (NumberFormatException npf) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}
	}

	private void definirFiltro(Scanner input, TipoFiltro tipoFiltro, String opcaoEscolhida,
			MenuHistorico menuHistorico) {
		int indexFiltro = Integer.parseInt(opcaoEscolhida);
		var filtro = gerenciadorFiltrosOrientacao.pegarFiltroPossivel(tipoFiltro, indexFiltro);
		gerenciadorFiltrosOrientacao.adicionarFiltro(tipoFiltro, filtro);

	}

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
