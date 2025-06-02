package aplication.interfaces;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import infrastructure.dao.RegistroComandoDAO;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.commandos.Comando;
import service.commandos.ComandoAdicionarOrientacao;
import service.commandos.ExecutadorComando;
import service.exceptions.NivelDeAcessoInsuficienteException;
import service.exceptions.orientacao.TituloNaoDisponivelException;

public class MenuAdicaoOrientacao extends Menu implements Executor {

	private ExecutadorComando executadorComando;

	private OrientacaoService orientacaoService;
	private SessaoUsuario sessaoUsuario;

	private OrientacaoDto orientacaoCriar;

	private String id_orientacao;

	public MenuAdicaoOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService,
			SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.orientacaoService = orientacaoService;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		Menu proximoMenu = null;
		List<OrientacaoDto> listaOrientacaoCriada = null;

		try {
			listaOrientacaoCriada = idiomaImplementacao.mostrarMenuCriarOrientacao(input);
			criarOrientacoes(listaOrientacaoCriada);
			proximoMenu = devolverOpcaoEscolhida(TipoMenu.CERTO, idiomaImplementacao, menuHistorico);
			menuHistorico.definirProximoMenu(proximoMenu);
			
		} catch (NivelDeAcessoInsuficienteException naie) {
			menuHistorico.definirProximoMenu(MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemNivelDeAcessoInsuficiente(), idiomaImplementacao));

		} catch (TituloNaoDisponivelException tnde) {
			menuHistorico.definirProximoMenu(MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemTituloNaoDisponivel(), idiomaImplementacao));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			menuHistorico.voltarMenu();
		}

	}

	public void criarOrientacoes(List<OrientacaoDto> listaOrientacaoCriada) {
		id_orientacao = UUID.randomUUID().toString();

		for (OrientacaoDto orientacao : listaOrientacaoCriada) {
			orientacaoCriar = orientacao;
			executar();
		}
	}

	public Menu devolverOpcaoEscolhida(TipoMenu opcao, IdiomaImplementacao idiomaImplementacao,
			MenuHistorico menuHistorico) {
		return switch (opcao) {
		case CERTO -> MenuFactory.criarMenuResultado(opcao, menuHistorico.pegarMenuAnterior(),
				idiomaImplementacao.pegarMensangemAdicaoConcluida(), idiomaImplementacao);
		case FALHA -> MenuFactory.criarMenuResultado(opcao, menuHistorico.pegarMenuAnterior(),
				idiomaImplementacao.pegarMensangemAdicaoFalhada(), idiomaImplementacao);
		default -> this;
		};
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public void executar() {
		criarExecutadorComando();
		executadorComando.aplicarComando(idiomaImplementacao);
	}

	@Override
	public Comando pegarComando() {
		return new ComandoAdicionarOrientacao(sessaoUsuario, orientacaoCriar, orientacaoService, id_orientacao);
	}

	@Override
	public void criarExecutadorComando() {
		this.executadorComando = ExecutadorComando.criarExecutadorComando(pegarComando(), new RegistroComandoDAO());
	}

}
