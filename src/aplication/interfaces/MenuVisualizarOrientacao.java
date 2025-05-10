package aplication.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.lang.NullPointerException;

import Dominio.IdiomaOrientacao;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.formatacao.FormatacaoListaOrientacao;

public class MenuVisualizarOrientacao implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoDto orientacaoDto;
	private final Menu menuAnterior;
	private final OrientacaoService orientacaoService;
	private final FormatacaoListaOrientacao formatador;

	public MenuVisualizarOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoDto orientacaoDto,
			Menu menuAnterior, OrientacaoService orientacaoService, FormatacaoListaOrientacao formatador) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoDto = orientacaoDto;
		this.menuAnterior = menuAnterior;
		this.orientacaoService = orientacaoService;
		this.formatador = formatador;
	}

	@Override
	public Menu chamarMenu(Scanner input) {
		Map<IdiomaOrientacao, OrientacaoDto> listaOrientacoesIdiomas = orientacaoService
				.pegarOrientacoesIdiomas(orientacaoService.pegarIdOrientacao(orientacaoDto));

		var listaOrdenada = gerarListaOrdenada(listaOrientacoesIdiomas);

		formatador.formatarOrientacoesIdiomas(null, listaOrientacoesIdiomas.size());

		String opcao = idiomaImplementacao.mostrarOrientacao(input, orientacaoDto);

		return devolverOpcaoMenu(opcao, listaOrdenada, listaOrientacoesIdiomas);
	}

	public Menu devolverOpcaoMenu(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		return switch (opcao.toUpperCase()) {
		case "E" -> MenuFactory.criarMenuPesquisa(TipoMenu.EDICAO_ORIENTACAO, orientacaoDto, menuAnterior);
		case "S" -> this.menuAnterior;
		default -> processarOpcao(opcao, listaOrdenada, listaComOrientacoes);
		};
	}

	public List<IdiomaOrientacao> gerarListaOrdenada(Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		var listaDisponivel = transformarMapEmList(listaComOrientacoes);
		var listaIndisponivel = pegarOrientacoeNaoDisponiveis(listaDisponivel);

		listaDisponivel.addAll(listaIndisponivel);
		return listaDisponivel;

	}

	@SuppressWarnings("finally")
	private Menu processarOpcao(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		Menu menuResultado = null;

		try {
			int opcaoEscolhida = Integer.parseInt(opcao);
			var orientacao = pegarOrientacao(opcaoEscolhida, listaOrdenada, listaComOrientacoes);
			menuResultado = MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao, this.menuAnterior);
		} catch (NumberFormatException e) {
			menuResultado = this;
		} finally {
			return menuResultado;
		}

	}

	private OrientacaoDto pegarOrientacao(int opcaoEscolhida, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		IdiomaOrientacao idiomaOrientacao = listaOrdenada.get(opcaoEscolhida - 1);

		return listaComOrientacoes.get(idiomaOrientacao);
	}

	private List<IdiomaOrientacao> transformarMapEmList(Map<IdiomaOrientacao, OrientacaoDto> lista) {
		return new ArrayList<>(lista.keySet());
	}

	private List<IdiomaOrientacao> pegarOrientacoeNaoDisponiveis(List<IdiomaOrientacao> listaDisponivel) {
		var listaNaoDisponivel = IdiomaOrientacao.listarIdiomas();

		for (IdiomaOrientacao idioma : listaDisponivel) {
			listaNaoDisponivel.remove(idioma);
		}

		return listaNaoDisponivel;
	}
	
	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}


}
