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
	public Menu chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		List<OrientacaoDto> listaOrientacaoCriada;
		try {
			listaOrientacaoCriada = idiomaImplementacao.mostrarMenuCriarOrientacao(input);
		} catch (Exception e) {
			return MenuFactory.criarMenu(TipoMenu.GERAL);
		}
		
		orientacaoService.criarOrientacoes(listaOrientacaoCriada);
		return devolverOpcaoEscolhida(TipoMenu.CERTO, idiomaImplementacao);
	}

	public Menu devolverOpcaoEscolhida(TipoMenu opcao, IdiomaImplementacao idiomaImplementacao) {
		return switch(opcao) {
		case CERTO -> MenuFactory.criarMenuResultado(opcao, MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao), idiomaImplementacao.pegarMensangemAdicaoConcluida());
		case FALHA -> MenuFactory.criarMenuResultado(opcao, MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao), idiomaImplementacao.pegarMensangemAdicaoFalhada());
		default -> this;
		};
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
