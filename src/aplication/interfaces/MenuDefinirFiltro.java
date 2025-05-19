package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import Dominio.Filtro;
import Dominio.IdiomaOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.filtros.FiltroFactory;
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

			IdiomaOrientacao idioma = idiomaImplementacao.obterIdiomaOrientacao();

			List<String> listaFiltros = tipoFiltro.getEnumFiltro().pegarValoresSegundoIdioma(idioma);

			String filtrosFormatados = formatacaoLista.formatarString(listaFiltros);

			String opcaoEscolhida = idiomaImplementacao.mostrarMenuVisualizarFiltros(input, filtrosFormatados,
					tipoFiltro.pegarNome(idioma));

			switch (opcaoEscolhida.trim().toUpperCase()) {
			case "V" -> opcaoEscolhida = "Voltando";
			default -> definirFiltro(input, tipoFiltro, opcaoEscolhida, menuHistorico);
			}

		} catch (NumberFormatException npf) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}
	}

	private void definirFiltro(Scanner input, TipoFiltro tipoFiltro, String opcaoEscolhida,
			MenuHistorico menuHistorico) {
		try {
			int indexFiltro = Integer.parseInt(opcaoEscolhida);
			var filtroEscolhido = tipoFiltro.getEnumFiltro().pegarValor(indexFiltro);

			boolean adicionou = gerenciadorFiltrosOrientacao.adicionarFiltro(tipoFiltro, filtroEscolhido.name());

			menuHistorico.voltarMenu(MenuFactory.criarMenuComFiltros(TipoMenu.FILTRO_GERAL,
					gerenciadorFiltrosOrientacao, idiomaImplementacao));

			if (adicionou) {
				menuHistorico.definirProximoMenu(
						MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuHistorico.pegarMenuAtual(),
								idiomaImplementacao.pegarMensagemAdicionadoNovoFiltro(input), idiomaImplementacao));
			} else {
				menuHistorico
						.definirProximoMenu(MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.voltarMenu(),
								idiomaImplementacao.pegarMensagemFalhaAdicionarFiltro(input), idiomaImplementacao));
			}

		} catch (NumberFormatException npe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());

		} catch (NullPointerException npe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}

	}

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
