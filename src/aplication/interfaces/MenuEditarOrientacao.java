package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class MenuEditarOrientacao implements Menu {

	private final OrientacaoDto orientacaoDto;
	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;

	public MenuEditarOrientacao(OrientacaoDto orientacaoDto, IdiomaImplementacao idiomaImplementacao,
			OrientacaoService orientacaoService) {
		this.orientacaoDto = orientacaoDto;
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		var orientacaoAlterada = orientacaoDto;

		String opcaoEscolhida = idiomaImplementacao.mostrarMenuEditarOrientacao(input);

		switch (opcaoEscolhida.trim().toUpperCase()) {
		case "V" -> confirmarSaida(input, menuHistorico, orientacaoAlterada);
		}

	}

	public void confirmarSaida(Scanner input, MenuHistorico menuHistorico, OrientacaoDto orientacaoAlterada) {
		if (!orientacaoAlterada.equals(orientacaoDto)) {
			String confirmacao = idiomaImplementacao.mostrarMenuConfirmarEdicao(input);

			switch (confirmacao.trim().toUpperCase()) {
			case "1":
				salvarAlteracaoOrientacao(orientacaoAlterada, menuHistorico);
				break;
			}
		
		} else {
			menuHistorico.voltarMenu();
		}
	}

	public void salvarAlteracaoOrientacao(OrientacaoDto orientacaoAlterada, MenuHistorico menuHistorico) {
		boolean atualizacaoOrientacao = orientacaoService.atualizarOrientacao(orientacaoAlterada,
				orientacaoService.pegarIdOrientacao(orientacaoDto));
		
		if (atualizacaoOrientacao) {
			menuHistorico.voltarMenu();
			var proximoMenu = MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaoAlterada, idiomaImplementacao);
			menuHistorico.definirProximoMenu(MenuFactory.criarMenuResultado(TipoMenu.CERTO, proximoMenu, idiomaImplementacao.pegarMensagemEdicaoConcluida(), idiomaImplementacao));
		} else {
		}
	}

	public Menu pegarMenuAnterior(MenuHistorico menuHistorico, OrientacaoDto orientacaoAlterada) {
		try {
			return menuHistorico.voltarMenu();
		} catch (Exception e) {
			return MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao);
		}

	}

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;

	}
}
