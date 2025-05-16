package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class MenuAdicaoOrientacao implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private OrientacaoService orientacaoService;

	public MenuAdicaoOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		Menu proximoMenu = null;
		List<OrientacaoDto> listaOrientacaoCriada = null;
		try {
			listaOrientacaoCriada = idiomaImplementacao.mostrarMenuCriarOrientacao(input);
			orientacaoService.criarOrientacoes(listaOrientacaoCriada);
			proximoMenu = devolverOpcaoEscolhida(TipoMenu.CERTO, idiomaImplementacao);
			menuHistorico.definirProximoMenu(proximoMenu);
		} catch (Exception e) {
			menuHistorico.voltarMenu();
		}

	}

	public Menu devolverOpcaoEscolhida(TipoMenu opcao, IdiomaImplementacao idiomaImplementacao) {
		return switch (opcao) {
		case CERTO -> MenuFactory.criarMenuResultado(opcao, MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao),
				idiomaImplementacao.pegarMensangemAdicaoConcluida(), idiomaImplementacao);
		case FALHA -> MenuFactory.criarMenuResultado(opcao, MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao),
				idiomaImplementacao.pegarMensangemAdicaoFalhada(), idiomaImplementacao);
		default -> this;
		};
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
