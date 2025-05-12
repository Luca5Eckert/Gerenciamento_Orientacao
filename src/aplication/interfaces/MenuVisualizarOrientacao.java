package aplication.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.OrientacaoNaoDisponivelIdiomaException;
import aplication.interfaces.exceptions.SairMenuException;
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
		try {
			Map<IdiomaOrientacao, OrientacaoDto> listaOrientacoesIdiomas = orientacaoService
					.pegarOrientacoesIdiomas(orientacaoService.pegarIdOrientacao(orientacaoDto));
			var listaOrdenada = gerarListaOrdenada(listaOrientacoesIdiomas);

			String orientacoesFormatada = formatador.formatarOrientacoesIdiomas(listaOrdenada,
					listaOrientacoesIdiomas.size());

			String opcao = idiomaImplementacao.mostrarOrientacao(input, orientacaoDto, orientacoesFormatada);

			return devolverOpcaoMenu(opcao, listaOrdenada, listaOrientacoesIdiomas, input);

		} catch (Exception e) {
			e.printStackTrace();
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, this, "FNDJA");
		}

	}

	public Menu devolverOpcaoMenu(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes, Scanner input) {
		return switch (opcao.toUpperCase()) {
		case "E" -> MenuFactory.criarMenuPesquisa(TipoMenu.EDICAO_ORIENTACAO, orientacaoDto, menuAnterior);
		case "S" -> this.menuAnterior;
		case "R" -> removerOrientacao(input);
		default -> processarOpcao(opcao, listaOrdenada, listaComOrientacoes);
		};
	}

	public Menu removerOrientacao(Scanner input) {
		try {
			idiomaImplementacao.mostrarMenuConfirmarApagarOrientacao(input);
			orientacaoService.removerOrientacao(orientacaoDto);
			return MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuAnterior,
					idiomaImplementacao.pegarMensagemRemoverComSucessoOrientacao());
		} catch (SairMenuException sme) {
			return this;
		} catch (Exception le) {
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, this,
					idiomaImplementacao.pegarMensagemErroAoRemoverOrientacao());
		}
	}

	public List<IdiomaOrientacao> gerarListaOrdenada(Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		var listaDisponivel = transformarMapEmList(listaComOrientacoes);
		var listaIndisponivel = pegarOrientacoeNaoDisponiveis(listaDisponivel);

		listaDisponivel.addAll(listaIndisponivel);
		return listaDisponivel;

	}

	private Menu processarOpcao(String opcao, List<IdiomaOrientacao> listaOrdenada,
			Map<IdiomaOrientacao, OrientacaoDto> listaComOrientacoes) {
		int opcaoEscolhida = 0;
		try {
			opcaoEscolhida = Integer.parseInt(opcao) - 1;
			var orientacao = pegarOrientacao(opcaoEscolhida, listaOrdenada, listaComOrientacoes);
			return MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacao, this.menuAnterior);
		} catch (NullPointerException npe) {
			return this;
		} catch (OrientacaoNaoDisponivelIdiomaException ondie) {
			IdiomaOrientacao idiomaOrientacao = listaOrdenada.get(opcaoEscolhida);
			return MenuFactory.criarMenuAdicionarNovoIdiomaOrientacao(TipoMenu.ADICAO_ORIENTACAO, this, orientacaoDto,
					idiomaOrientacao);
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
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}
}
