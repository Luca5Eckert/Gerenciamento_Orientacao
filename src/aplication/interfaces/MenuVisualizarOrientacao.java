package aplication.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.OrientacaoNaoDisponivelIdiomaException;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.formatacao.FormatacaoListaComDivisoria;

public class MenuVisualizarOrientacao extends Menu {

	private final OrientacaoDto orientacaoDto;
	private final OrientacaoService orientacaoService;
	private final FormatacaoListaComDivisoria formatador;
	private SessaoUsuario sessaoUsuario;

	public MenuVisualizarOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoDto orientacaoDto,
			OrientacaoService orientacaoService, FormatacaoListaComDivisoria formatador, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.orientacaoDto = orientacaoDto;
		this.orientacaoService = orientacaoService;
		this.formatador = formatador;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		try {
			Map<IdiomaOrientacao, OrientacaoDto> listaOrientacoesIdiomas = orientacaoService
					.pegarOrientacoesIdiomas(orientacaoService.pegarIdOrientacao(orientacaoDto));
			var listaOrdenada = gerarListaOrdenada(listaOrientacoesIdiomas);

			String orientacoesFormatada = formatador.formatar(listaOrdenada, listaOrientacoesIdiomas.size(),
					idiomaImplementacao);

			String opcao = idiomaImplementacao.mostrarOrientacao(input, orientacaoDto, orientacoesFormatada);

			devolverOpcaoMenu(opcao, listaOrdenada, listaOrientacoesIdiomas, input, menuHistorico);

		} catch (Exception e) {
			System.out.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}

	}

	public void devolverOpcaoMenu(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes, Scanner input, MenuHistorico menuHistorico) {
		switch (opcao.trim().toUpperCase()) {
		case "E" -> menuHistorico.definirProximoMenu(MenuFactory.criarMenuPesquisa(TipoMenu.EDICAO_ORIENTACAO,
				orientacaoDto, idiomaImplementacao, sessaoUsuario));
		case "S" -> menuHistorico.voltarMenu();
		case "A" -> removerOrientacao(input, menuHistorico);
		default -> processarOpcao(opcao, listaOrdenada, listaComOrientacoes, menuHistorico);
		}
		;
	}

	public void removerOrientacao(Scanner input, MenuHistorico menuHistorico) {
		menuHistorico.definirProximoMenu(MenuFactory.criarMenuPesquisa(TipoMenu.APAGAR_ORIENTACAO, orientacaoDto,
				idiomaImplementacao, sessaoUsuario));
	}

	public List<IdiomaOrientacao> gerarListaOrdenada(Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		var listaDisponivel = transformarMapEmList(listaComOrientacoes);
		var listaIndisponivel = pegarOrientacoeNaoDisponiveis(listaDisponivel);

		listaDisponivel.addAll(listaIndisponivel);
		return listaDisponivel;

	}

	private void processarOpcao(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes, MenuHistorico menuHistorico) {
		int opcaoEscolhida = 0;

		try {
			opcaoEscolhida = Integer.parseInt(opcao) - 1;
			var orientacao = pegarOrientacao(opcaoEscolhida, listaOrdenada, listaComOrientacoes);
			var proximoMenu = MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao,
					idiomaImplementacao, sessaoUsuario);
			menuHistorico.trocarMenuAtual(proximoMenu);

		} catch (NullPointerException npe) {
			System.out.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		} catch (OrientacaoNaoDisponivelIdiomaException ondie) {
			IdiomaOrientacao idiomaOrientacao = listaOrdenada.get(opcaoEscolhida);
			var proximoMenu = MenuFactory.criarMenuAdicionarNovoIdiomaOrientacao(TipoMenu.ADICAO_ORIENTACAO, this,
					orientacaoDto, idiomaOrientacao, idiomaImplementacao, sessaoUsuario);
			menuHistorico.definirProximoMenu(proximoMenu);

		} catch (NumberFormatException nfe) {
			System.out.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}
	}

	private OrientacaoDto pegarOrientacao(int opcaoEscolhida, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes)
			throws NullPointerException, OrientacaoNaoDisponivelIdiomaException {
		IdiomaOrientacao idiomaOrientacao = listaOrdenada.get(opcaoEscolhida);

		if (listaOrdenada.size() > opcaoEscolhida && opcaoEscolhida >= listaComOrientacoes.size()) {
			throw new OrientacaoNaoDisponivelIdiomaException();
		} else if (opcaoEscolhida >= listaOrdenada.size()) {
			throw new NullPointerException();
		}

		return listaComOrientacoes.get(idiomaOrientacao);
	}

	private List<IdiomaOrientacao> transformarMapEmList(Map<IdiomaOrientacao, OrientacaoDto> lista) {
		return new ArrayList<>(lista.keySet());
	}

	private List<IdiomaOrientacao> pegarOrientacoeNaoDisponiveis(List<IdiomaOrientacao> listaDisponivel) {
		var listaNaoDisponivel = new ArrayList<>(IdiomaOrientacao.listarIdiomas());

		for (IdiomaOrientacao idioma : listaDisponivel) {
			listaNaoDisponivel.remove(idioma);
		}

		return listaNaoDisponivel;
	}

}
