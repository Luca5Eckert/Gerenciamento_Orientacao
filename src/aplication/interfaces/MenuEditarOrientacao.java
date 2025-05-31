package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.OrientacaoNaoDisponivelIdiomaException;
import dtos.OrientacaoDto;
import infrastructure.dao.RegistroComandoDAO;
import infrastructure.utilitarios.FormatacaoUtil;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.commandos.Comando;
import service.commandos.ComandoEditarOrientacao;
import service.commandos.ExecutadorComando;

public class MenuEditarOrientacao extends Menu implements Executor {

	private ExecutadorComando executadorComando;

	private OrientacaoDto orientacaoDto;
	private final OrientacaoService orientacaoService;
	private SessaoUsuario sessaoUsuario;

	private OrientacaoDto orientacaoAlterada;

	private String tituloOrientacao;
	private String conteudoOrientacao;
	private TipoOrientacao tipoOrientacao;
	private IdiomaOrientacao idiomaOrientacao;

	public MenuEditarOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoDto orientacaoDto,
			OrientacaoService orientacaoService, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.orientacaoDto = orientacaoDto;
		this.orientacaoService = orientacaoService;
		this.sessaoUsuario = sessaoUsuario;

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
			orientacaoAlterada = criarOrientacaoAlterada(tituloOrientacao, conteudoOrientacao, tipoOrientacao,
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
		String novoTitulo = idiomaImplementacao.mostrarMenuMudarTituloOrientacao(input,
				FormatacaoUtil.enquadrarTextoNoMenu(orientacaoDto.titulo(), 59, 1));

		if (!novoTitulo.toUpperCase().trim().equals("V")) {
			this.tituloOrientacao = novoTitulo;
			idiomaImplementacao.mostrarMenuAlteradoAtributoComSucesso();
		}

	}

	private void editarConteudo(Scanner input) {
		String novoConteudo = idiomaImplementacao.mostrarMenuMudarConteudoOrientacao(input,
				FormatacaoUtil.enquadrarTextoNoMenu(orientacaoDto.titulo(), 59, 1));

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
		Menu proximoMenu = null;

		try {
			if (verificarAlterouTipo()) {
				executar();

				menuHistorico.voltarPonteiro(2);

			} else {
				var listaOrientacoesId = orientacaoService.pegarOrientacoesPorId(orientacaoDto);

				alterarOrientações(listaOrientacoesId);
			}

			var menuCorreto = MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaoAlterada,
					idiomaImplementacao, sessaoUsuario);

			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuCorreto,
					idiomaImplementacao.pegarMensagemEdicaoConcluida(), idiomaImplementacao);

		} catch (OrientacaoNaoDisponivelIdiomaException ondi) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.pegarMenuAnterior(),
					ondi.getMessage(), idiomaImplementacao);
			menuHistorico.voltarPonteiro(2);
		}

		menuHistorico.definirProximoMenu(proximoMenu);
	}

	private void alterarOrientações(List<OrientacaoDto> listaOrientacoesId) {
		for (OrientacaoDto orientacao : listaOrientacoesId) {
			orientacaoDto = orientacao;
			orientacaoAlterada = new OrientacaoDto(orientacao.titulo(), orientacaoAlterada.tipoOrientacao(),
					orientacao.conteudo(), orientacao.idiomaOrientacao());
			executar();
		}

	}

	private boolean verificarAlterouTipo() {
		return orientacaoDto.tipoOrientacao().equals(orientacaoAlterada.tipoOrientacao());
	}

	@Override
	public void executar() {
		criarExecutadorComando();
		executadorComando.aplicarComando(idiomaImplementacao);
	}

	@Override
	public Comando pegarComando() {
		return new ComandoEditarOrientacao(sessaoUsuario, orientacaoService, orientacaoDto, orientacaoAlterada,
				idiomaImplementacao);
	}

	@Override
	public void criarExecutadorComando() {
		this.executadorComando = ExecutadorComando.criarExecutadorComando(pegarComando(), sessaoUsuario,
				new RegistroComandoDAO());
	}

}
