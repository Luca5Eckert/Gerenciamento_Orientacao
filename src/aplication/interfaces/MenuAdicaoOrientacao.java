package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import service.OrientacaoService;

public class MenuAdicaoOrientacao implements Menu {
	private OrientacaoService orientacaoService;

	public MenuAdicaoOrientacao(OrientacaoService orientacaoService) {
		this.orientacaoService = orientacaoService;
	}
	
	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		var listaOrientacaoCriada = idiomaImplementacao.mostrarMenuCriarOrientacao(input);
		
		orientacaoService.criarOrientacoes(listaOrientacaoCriada);
		return devolverOpcaoEscolhida(TipoMenu.CERTO, idiomaImplementacao);
	}

	public Menu devolverOpcaoEscolhida(TipoMenu opcao, IdiomaImplementacao idiomaImplementacao) {
		return switch(opcao) {
		case CERTO -> MenuFactory.criarMenuResultado(opcao, MenuFactory.criarMenu(TipoMenu.GERAL), idiomaImplementacao.pegarMensangemAdicaoConcluida());
		case FALHA -> MenuFactory.criarMenuResultado(opcao, MenuFactory.criarMenu(TipoMenu.GERAL), idiomaImplementacao.pegarMensangemAdicaoFalhada());
		default -> this;
		};
	}

}
