package aplication.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.exceptions.orientacao.OrientacaoException;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.formatacao.FormatacaoNumerarLista;

public class MenuExibirOrientacoes implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;
	private final GerenciadorFiltrosOrientacao gerenciadorFiltro;
	private final FormatacaoNumerarLista formatacaoLista;

	public MenuExibirOrientacoes(OrientacaoService orientacaoService, GerenciadorFiltrosOrientacao gerenciadorFiltro,
			FormatacaoNumerarLista formatacaoLista, IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
		this.gerenciadorFiltro = gerenciadorFiltro;
		this.formatacaoLista = formatacaoLista;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		List<OrientacaoDto> orientacoesFiltradas = new ArrayList<>();
		String textoFormatado;

		try {
			orientacoesFiltradas = orientacaoService.aplicarFiltro(gerenciadorFiltro);
		} catch (OrientacaoException e) {
			textoFormatado = idiomaImplementacao.pegarMensagemOrientacoesNaoEncontrada();
		}

		textoFormatado = formatacaoLista.formatar(orientacoesFiltradas, idiomaImplementacao);

		String palavra = obterPalavraPesquisada();
		String opcao = idiomaImplementacao.mostrarMenuOrientacaoDisponiveis(input, textoFormatado, palavra);

		var proximoMenu = processarOpcao(opcao, idiomaImplementacao, orientacoesFiltradas);

		menuHistorico.definirProximoMenu(proximoMenu);

	}

	private String obterPalavraPesquisada() {
		String palavra = gerenciadorFiltro.getPalavraBuscada();
		return palavra != null ? palavra : "";
	}

	private Menu processarOpcao(String opcao, IdiomaImplementacao idiomaImplementacao,
			List<OrientacaoDto> orientacoesFiltradas) {
		return switch (opcao.trim().toUpperCase()) {
		case "F" -> MenuFactory.criarMenuComFiltros(TipoMenu.FILTRO, gerenciadorFiltro, idiomaImplementacao);
		case "P" ->
			MenuFactory.criarMenuComFiltros(TipoMenu.PESQUISA_ORIENTACAO, gerenciadorFiltro, idiomaImplementacao);
		case "A" -> limparPesquisa();
		case "R" -> limparFiltros(idiomaImplementacao);
		case "S" -> MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao);
		default -> criarMenuPesquisa(opcao, orientacoesFiltradas);
		};
	}

	private Menu criarMenuPesquisa(String opcao, List<OrientacaoDto> listaOrientacao) {
		try {
			int numeroOrientacao = Integer.parseInt(opcao);
			var orientacao = listaOrientacao.get(numeroOrientacao - 1);
			return MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao, idiomaImplementacao);
		} catch (Exception e) {
			return this;
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
