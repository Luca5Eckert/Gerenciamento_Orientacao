package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.OrientacaoNaoDisponivelIdiomaException;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class MenuEditarOrientacao extends Menu {

	private final OrientacaoDto orientacaoDto;
	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;

	private String tituloOrientacao;
	private String conteudoOrientacao;
	private TipoOrientacao tipoOrientacao;
	private IdiomaOrientacao idiomaOrientacao;

	public MenuEditarOrientacao(OrientacaoDto orientacaoDto, IdiomaImplementacao idiomaImplementacao,
			OrientacaoService orientacaoService) {
		super(idiomaImplementacao);
		this.orientacaoDto = orientacaoDto;
		this.orientacaoService = orientacaoService;

		this.tituloOrientacao = orientacaoDto.titulo();
		this.conteudoOrientacao = orientacaoDto.conteudo();
		this.tipoOrientacao = orientacaoDto.tipoOrientacao();
		this.idiomaOrientacao = orientacaoDto.idiomaOrientacao();
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuEditarOrientacao(input);

		switch (opcaoEscolhida.trim().toUpperCase()) {
		case "V":
			var orientacaoAlterada = criarOrientacaoAlterada(tituloOrientacao, conteudoOrientacao, tipoOrientacao,
					idiomaOrientacao);
			confirmarSaida(input, menuHistorico, orientacaoAlterada);
			break;
		case "1":
			editarTitulo(input);
			break;
		case "2":
			editarTipo(input);
			break;
		case "3":
			editarConteudo(input);
			break;
		case "4":
			editarIdioma(input);
			break;
		}

	}

	private OrientacaoDto criarOrientacaoAlterada(String tituloOrientacao, String conteudoOrientacao,
			TipoOrientacao tipoOrientacao, IdiomaOrientacao idiomaOrientacao) {
		return new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudoOrientacao, idiomaOrientacao);
	}

	private void editarTitulo(Scanner input) {
		String novoTitulo = idiomaImplementacao.mostrarMenuMudarTituloOrientacao(input, orientacaoDto.titulo());

		if (!novoTitulo.toUpperCase().trim().equals("V")) {
			this.tituloOrientacao = novoTitulo;
			idiomaImplementacao.mostrarMenuAlteradoAtributoComSucesso();
		}

	}

	private void editarConteudo(Scanner input) {
		String novoConteudo = idiomaImplementacao.mostrarMenuMudarConteudoOrientacao(input, orientacaoDto.conteudo());

		if (!novoConteudo.equals("V")) {
			this.conteudoOrientacao = novoConteudo;
			idiomaImplementacao.mostrarMenuAlteradoAtributoComSucesso();
		}
	}

	private void editarTipo(Scanner input) {
		var idiomaUsuario = idiomaImplementacao.obterIdiomaOrientacao();

		String tiposOrientacoes = TipoOrientacao.mostrarTodasTipos(idiomaUsuario);

		String novoTipo = idiomaImplementacao.mostrarMenuMudarTipoOrientacao(input,
				orientacaoDto.tipoOrientacao().getNome(idiomaUsuario), tiposOrientacoes);

		try {
			if (!novoTipo.toUpperCase().trim().equals("V")) {
				String confirmacao = idiomaImplementacao.mostrarMenuConfirmarMudancaTipo(input);

				switch (confirmacao.trim().toUpperCase()) {
				case "A":
					tipoOrientacao = TipoOrientacao.pegarOrientacao(Integer.parseInt(novoTipo));
					idiomaImplementacao.mostrarMenuAlteradoAtributoComSucesso();
					break;
				}

			}

		} catch (NumberFormatException nfe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}

	}

	private void editarIdioma(Scanner input) {
		var idiomaUsuario = idiomaImplementacao.obterIdiomaOrientacao();

		String idiomaOrientacoes = IdiomaOrientacao.listaIdiomasFormatado(idiomaImplementacao);

		String novoIdioma = idiomaImplementacao.mostrarMenuMudarIdiomaOrientacao(input,
				orientacaoDto.idiomaOrientacao().getNomePorIdioma(idiomaUsuario), idiomaOrientacoes);

		try {
			if (!novoIdioma.toUpperCase().trim().equals("V")) {
				idiomaOrientacao = IdiomaOrientacao.pegarIdioma(Integer.parseInt(novoIdioma) - 1);
				idiomaImplementacao.mostrarMenuAlteradoAtributoComSucesso();
			}
		} catch (NumberFormatException nfe) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}

	}

	public void confirmarSaida(Scanner input, MenuHistorico menuHistorico, OrientacaoDto orientacaoAlterada) {
		if (!orientacaoAlterada.equals(orientacaoDto)) {
			String confirmacao = idiomaImplementacao.mostrarMenuConfirmarEdicao(input);

			switch (confirmacao.trim().toUpperCase()) {
			case "A":
				salvarAlteracaoOrientacao(orientacaoAlterada, menuHistorico);
				break;
			}

		} else {
			menuHistorico.voltarMenu();
		}
	}

	public void salvarAlteracaoOrientacao(OrientacaoDto orientacaoAlterada, MenuHistorico menuHistorico) {
		Menu proximoMenu;

		try {

			boolean atualizacaoOrientacao = orientacaoService.atualizarOrientacao(orientacaoAlterada, orientacaoDto,
					idiomaImplementacao);

			if (atualizacaoOrientacao) {
				menuHistorico.voltarPonteiro(2);

				var menuCorreto = MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaoAlterada,
						idiomaImplementacao);

				proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuCorreto,
						idiomaImplementacao.pegarMensagemEdicaoConcluida(), idiomaImplementacao);

			} else {
				proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, this,
						idiomaImplementacao.pegarMensagemEdicaoFalha(), idiomaImplementacao);
			}

		} catch (OrientacaoNaoDisponivelIdiomaException ondi) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.pegarMenuAnterior(),
					ondi.getMessage(), idiomaImplementacao);
			menuHistorico.voltarPonteiro(2);
		}

		menuHistorico.definirProximoMenu(proximoMenu);
	}


}
