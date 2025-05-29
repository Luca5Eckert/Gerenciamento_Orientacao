package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.filtros.TipoFiltro;
import service.formatacao.FormatacaoNumerarLista;

public class MenuExibirFiltros extends Menu {

	private GerenciadorFiltrosOrientacao gerenciadorFiltro;
	private FormatacaoNumerarLista formatacaoLista;
	private SessaoUsuario sessaoUsuario;

	public MenuExibirFiltros(IdiomaImplementacao idiomaImplementacao, GerenciadorFiltrosOrientacao gerenciadorFiltro,
			FormatacaoNumerarLista formatacaoLista, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.idiomaImplementacao = idiomaImplementacao;
		this.gerenciadorFiltro = gerenciadorFiltro;
		this.formatacaoLista = formatacaoLista;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		List<TipoFiltro> tiposExistentes = gerenciadorFiltro.pegarTiposDeFiltros();

		String tipoOrientacoesDisponiveis = formatacaoLista.formatarTiposDeFiltro(tiposExistentes, idiomaImplementacao);

		String opcao = idiomaImplementacao.mostrarMenuVisualizarTiposFiltros(input, tipoOrientacoesDisponiveis);

		switch (opcao.trim().toUpperCase()) {
		case "V" -> menuHistorico.voltarMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.FILTRO_GERAL, gerenciadorFiltro, idiomaImplementacao));
		default -> visualizarTipoFiltroAtivados(input, opcao);
		}

	}

	private void visualizarTipoFiltroAtivados(Scanner input, String opcao) {
		try {
			int indexTipoFiltro = Integer.parseInt(opcao);

			TipoFiltro tipoFiltro = TipoFiltro.pegarTipoFiltroPorIndex(indexTipoFiltro);

			var listaFiltrosDisponiveis = gerenciadorFiltro.pegarFiltrosAtivadosEmTexto(tipoFiltro,
					idiomaImplementacao);

			String opcaoEscolhida = idiomaImplementacao.mostrarMenuVisualizarFiltrosDisponiveis(input,
					listaFiltrosDisponiveis, tipoFiltro.pegarNome(idiomaImplementacao.obterIdiomaOrientacao()));

			switch (opcaoEscolhida.trim().toUpperCase()) {
			case "D" -> apagarFiltro(input, tipoFiltro, listaFiltrosDisponiveis);
			}

		} catch (NumberFormatException npe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		} catch (Exception npe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}
	}

	public void apagarFiltro(Scanner input, TipoFiltro tipoFiltro, String filtrosFormatados) {
		try {
			String opcao = idiomaImplementacao.mostrarMenuApagarFiltro(input,
					tipoFiltro.pegarNome(idiomaImplementacao.obterIdiomaOrientacao()), filtrosFormatados);

			if (!opcao.trim().toUpperCase().equals("V")) {
				int indexFiltroApagar = Integer.parseInt(opcao);

				gerenciadorFiltro.apagarOrientacao(tipoFiltro, indexFiltroApagar);
			}

		} catch (NumberFormatException npe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		} catch (Exception npe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}
	}


}
