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
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.formatacao.FormatacaoListaComDivisoria;

public class MenuVisualizarOrientacao implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoDto orientacaoDto;
	private final OrientacaoService orientacaoService;
	private final FormatacaoListaComDivisoria formatador;

	public MenuVisualizarOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoDto orientacaoDto,
			OrientacaoService orientacaoService, FormatacaoListaComDivisoria formatador) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoDto = orientacaoDto;
		this.orientacaoService = orientacaoService;
		this.formatador = formatador;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		Menu proximoMenu = null;

		try {
			Map<IdiomaOrientacao, OrientacaoDto> listaOrientacoesIdiomas = orientacaoService
					.pegarOrientacoesIdiomas(orientacaoService.pegarIdOrientacao(orientacaoDto));
			var listaOrdenada = gerarListaOrdenada(listaOrientacoesIdiomas);

			String orientacoesFormatada = formatador.formatar(listaOrdenada, listaOrientacoesIdiomas.size(), idiomaImplementacao);

			String opcao = idiomaImplementacao.mostrarOrientacao(input, orientacaoDto, orientacoesFormatada);

			proximoMenu = devolverOpcaoMenu(opcao, listaOrdenada, listaOrientacoesIdiomas, input, menuHistorico);

		} catch (Exception e) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, this, idiomaImplementacao.pegarMensagemErro(),
					idiomaImplementacao);
		}
		
		menuHistorico.definirProximoMenu(proximoMenu);

	}

	public Menu devolverOpcaoMenu(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes, Scanner input, MenuHistorico menuHistorico) {
		return switch (opcao.toUpperCase()) {
		case "E" -> MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaoDto, idiomaImplementacao);
		case "S" -> menuHistorico.voltarMenu();
		case "A" -> removerOrientacao(input, menuHistorico);
		default -> processarOpcao(opcao, listaOrdenada, listaComOrientacoes, menuHistorico);
		};
	}

	public Menu removerOrientacao(Scanner input, MenuHistorico menuHistorico) {
		try {
			idiomaImplementacao.mostrarMenuConfirmarApagarOrientacao(input);
			orientacaoService.removerOrientacao(orientacaoDto);
			return MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemRemoverComSucessoOrientacao(), idiomaImplementacao);
		} catch (SairMenuException sme) {
			return this;
		} catch (Exception le) {
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, this,
					idiomaImplementacao.pegarMensagemErroAoRemoverOrientacao(), idiomaImplementacao);
		}
	}

	public List<IdiomaOrientacao> gerarListaOrdenada(Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		var listaDisponivel = transformarMapEmList(listaComOrientacoes);
		var listaIndisponivel = pegarOrientacoeNaoDisponiveis(listaDisponivel);

		listaDisponivel.addAll(listaIndisponivel);
		return listaDisponivel;

	}

	private Menu processarOpcao(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes, MenuHistorico menuHistorico) {
		int opcaoEscolhida = 0;
		try {
			opcaoEscolhida = Integer.parseInt(opcao) - 1;
			var orientacao = pegarOrientacao(opcaoEscolhida, listaOrdenada, listaComOrientacoes);
			return MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao, idiomaImplementacao);
		} catch (NullPointerException npe) {
			return this;
		} catch (OrientacaoNaoDisponivelIdiomaException ondie) {
			IdiomaOrientacao idiomaOrientacao = listaOrdenada.get(opcaoEscolhida);
			return MenuFactory.criarMenuAdicionarNovoIdiomaOrientacao(TipoMenu.ADICAO_ORIENTACAO, this, orientacaoDto,
					idiomaOrientacao, idiomaImplementacao);
		} catch (NumberFormatException nfe) {
			return this;
		}
	}

	private OrientacaoDto pegarOrientacao(int opcaoEscolhida, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes)
			throws NullPointerException, OrientacaoNaoDisponivelIdiomaException {
		IdiomaOrientacao idiomaOrientacao = listaOrdenada.get(opcaoEscolhida);

		if (listaOrdenada.size() > opcaoEscolhida && opcaoEscolhida >= listaComOrientacoes.size()) {
			IdiomaOrientacao idioma = listaOrdenada.get(opcaoEscolhida);
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

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		
	}
}
