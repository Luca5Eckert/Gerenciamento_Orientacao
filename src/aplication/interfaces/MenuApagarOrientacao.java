package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import infrastructure.dao.RegistroComandoDAO;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.commandos.Comando;
import service.commandos.ComandoRemoverOrientacao;
import service.commandos.ExecutadorComando;
import service.exceptions.ComandoHistoricoException;
import service.exceptions.NivelDeAcessoInsuficienteException;

public class MenuApagarOrientacao extends Menu implements Executor {

	private ExecutadorComando executadorComando;

	private SessaoUsuario sessaoUsuario;
	private OrientacaoService orientacaoService;
	private OrientacaoDto orientacaoDto;

	public MenuApagarOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService,
			OrientacaoDto orientacaoDto, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.orientacaoService = orientacaoService;
		this.orientacaoDto = orientacaoDto;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		try {
			escolherOpcaoDeletar(input, menuHistorico);

			menuHistorico.voltarMenu();
			var proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemRemoverComSucessoOrientacao(), idiomaImplementacao);
			menuHistorico.definirProximoMenu(proximoMenu);
		} catch (NivelDeAcessoInsuficienteException naie) {
			menuHistorico.definirProximoMenu(MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemNivelDeAcessoInsuficiente(), idiomaImplementacao));

		} catch (SairMenuException sme) {
			System.out.println(idiomaImplementacao.pegarMensagemVoltandoMenu());
		} catch (ComandoHistoricoException che) {
			System.out.println(idiomaImplementacao.pegarMensagemErroAoMexerNoHistorico());
		} catch (Exception le) {
			le.printStackTrace();
			System.out.println(le.getMessage());
			var proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemErroAoRemoverOrientacao(), idiomaImplementacao);
			menuHistorico.definirProximoMenu(proximoMenu);
		}
	}

	private void escolherOpcaoDeletar(Scanner input, MenuHistorico menuHistorico) throws SairMenuException {
		String opcaoDelete = idiomaImplementacao.mostrarMenuOpcaoApagarOrientacao(input);

		switch (opcaoDelete.trim().toUpperCase()) {
		case "T":
			if (confirmarSeDesejaApagar(input)) {
				menuHistorico.sobscreverMenu(menuHistorico.pegarMenuAnterior());
				apagarOrientacoes();
			}
			break;
		case "A":
			if (confirmarSeDesejaApagar(input)) {
				menuHistorico.sobscreverMenu(menuHistorico.pegarMenuAnterior());
				executar();
			}
			break;
		}

	}

	private void apagarOrientacoes() {
		List<OrientacaoDto> orientacaoPeloId = orientacaoService.pegarOrientacoesPorId(orientacaoDto);

		for (OrientacaoDto orientacao : orientacaoPeloId) {
			orientacaoDto = orientacao;
			executar();
		}
	}

	private boolean confirmarSeDesejaApagar(Scanner input) throws SairMenuException {
		String confirmar = idiomaImplementacao.mostrarMenuConfirmarApagarOrientacao(input);

		if (confirmar.trim().toUpperCase().equals("A")) {
			return true;
		}
		return false;
	}

	@Override
	public void executar() {
		criarExecutadorComando();
		executadorComando.aplicarComando(pegarComando());
	}

	@Override
	public Comando pegarComando() {
		return new ComandoRemoverOrientacao(sessaoUsuario.pegarIdUsuario(), orientacaoService, orientacaoDto);
	}

	@Override
	public void criarExecutadorComando() {
		executadorComando = new ExecutadorComando(sessaoUsuario, new RegistroComandoDAO());

	}

}
