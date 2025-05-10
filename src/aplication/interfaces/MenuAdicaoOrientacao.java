package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import service.OrientacaoService;

public class MenuAdicaoOrientacao implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private OrientacaoService orientacaoService;

	public MenuAdicaoOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
	}
	
	@Override
	public Menu chamarMenu( Scanner input) {
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

	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
