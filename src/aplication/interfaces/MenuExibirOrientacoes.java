package aplication.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.exceptions.orientacao.OrientacaoException;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.formatacao.FormatacaoNumerarLista;

public class MenuExibirOrientacoes extends Menu {

	private final OrientacaoService orientacaoService;
	private final GerenciadorFiltrosOrientacao gerenciadorFiltro;
	private final FormatacaoNumerarLista formatacaoLista;
	private SessaoUsuario sessaoUsuario;

	public MenuExibirOrientacoes(OrientacaoService orientacaoService, GerenciadorFiltrosOrientacao gerenciadorFiltro,
			FormatacaoNumerarLista formatacaoLista, IdiomaImplementacao idiomaImplementacao, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.orientacaoService = orientacaoService;
		this.gerenciadorFiltro = gerenciadorFiltro;
		this.formatacaoLista = formatacaoLista;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		List<OrientacaoDto> orientacoesFiltradas = new ArrayList<>();
		String textoFormatado = null;

		try {
			orientacoesFiltradas = orientacaoService.aplicarFiltro(gerenciadorFiltro);
			textoFormatado = formatacaoLista.formatarOrientacoesPorTitulo(orientacoesFiltradas, idiomaImplementacao);
		} catch (OrientacaoException oe) {
			textoFormatado = idiomaImplementacao.pegarMensagemOrientacoesNaoEncontrada();
		}

		var palavraPesquisada = obterPalavraPesquisada();
		
		String opcao = idiomaImplementacao.mostrarMenuOrientacaoDisponiveis(input, textoFormatado, palavraPesquisada);

		if (opcao.trim().toUpperCase().equals("V")) {
			menuHistorico.voltarMenu(MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao));
		} else {
			processarOpcao(opcao, idiomaImplementacao, orientacoesFiltradas, menuHistorico);

		}

	}

	private String obterPalavraPesquisada() {
		String palavra = gerenciadorFiltro.getPalavraBuscada();
		return palavra != null ? palavra : "";
	}

	private void processarOpcao(String opcao, IdiomaImplementacao idiomaImplementacao,
			List<OrientacaoDto> orientacoesFiltradas, MenuHistorico menuHistorico) {
		switch (opcao.trim().toUpperCase()) {
		case "F" -> irMenuFiltro(menuHistorico);
		case "P" -> irMenuPesquisa(menuHistorico);
		case "A" -> limparPesquisa();
		case "R" -> limparFiltros(idiomaImplementacao);
		default -> criarMenuPesquisa(opcao, orientacoesFiltradas, menuHistorico);
		}
		;
	}

	private void irMenuFiltro(MenuHistorico menuHistorico) {
		menuHistorico.definirProximoMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.FILTRO_GERAL, gerenciadorFiltro, idiomaImplementacao));

	}

	private void irMenuPesquisa(MenuHistorico menuHistorico) {
		menuHistorico.definirProximoMenu(
				MenuFactory.criarMenuComFiltros(TipoMenu.PESQUISA_ORIENTACAO, gerenciadorFiltro, idiomaImplementacao));
	}

	private void criarMenuPesquisa(String opcao, List<OrientacaoDto> listaOrientacao, MenuHistorico menuHistorico) {
		if (opcao.trim().toUpperCase().equals("L")) {
			return;
		}

		try {
			int numeroOrientacao = Integer.parseInt(opcao);
			var orientacao = listaOrientacao.get(numeroOrientacao - 1);
			menuHistorico.definirProximoMenu(
					MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao, idiomaImplementacao));
		} catch (Exception e) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
			;
		}
	}

	private Menu limparPesquisa() {
		gerenciadorFiltro.setPalavraBuscada(null);
		return this;
	}

	private Menu limparFiltros(IdiomaImplementacao idiomaImplementacao) {
		gerenciadorFiltro.limparFiltros();
		return this;
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
