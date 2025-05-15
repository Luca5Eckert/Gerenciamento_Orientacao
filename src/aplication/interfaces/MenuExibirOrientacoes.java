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
import service.formatacao.Formatacao;
import service.formatacao.FormatacaoListaOrientacao;

public class MenuExibirOrientacoes implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;
	private final GerenciadorFiltrosOrientacao gerenciadorFiltro;
	private final Formatacao<OrientacaoDto> formatacaoLista;

	public MenuExibirOrientacoes(OrientacaoService orientacaoService, GerenciadorFiltrosOrientacao gerenciadorFiltro,
			Formatacao<OrientacaoDto> formatacaoLista, IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
		this.gerenciadorFiltro = gerenciadorFiltro;
		this.formatacaoLista = formatacaoLista;
	}

	@Override
	public Menu chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		List<OrientacaoDto> orientacoesFiltradas = new ArrayList<>();
		try {
			orientacoesFiltradas = orientacaoService.aplicarFiltro(gerenciadorFiltro);
		} catch (OrientacaoException e) {
		}

		String textoFormatado = formatacaoLista.formatar(orientacoesFiltradas, idiomaImplementacao);

		if (resultadoVazio(textoFormatado)) {
			textoFormatado = idiomaImplementacao.pegarMensagemOrientacoesNaoEncontrada();
		}

		String palavra = obterPalavraPesquisada();
		String opcao = idiomaImplementacao.mostrarMenuOrientacaoDisponiveis(input, textoFormatado, palavra);

		return processarOpcao(opcao, idiomaImplementacao, orientacoesFiltradas);
	}

	private boolean resultadoVazio(String textoFormatado) {
		return textoFormatado == null || textoFormatado.isBlank();
	}

	private String obterPalavraPesquisada() {
		String palavra = gerenciadorFiltro.getPalavraBuscada();
		return palavra != null ? palavra : "";
	}

	private Menu processarOpcao(String opcao, IdiomaImplementacao idiomaImplementacao,
			List<OrientacaoDto> orientacoesFiltradas) {
		return switch (opcao.trim().toUpperCase()) {
		case "F" -> MenuFactory.criarMenuComFiltros(TipoMenu.FILTRO, gerenciadorFiltro, idiomaImplementacao);
		case "P" -> MenuFactory.criarMenuComFiltros(TipoMenu.PESQUISA_ORIENTACAO, gerenciadorFiltro, idiomaImplementacao);
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
			return MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao, this);
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
