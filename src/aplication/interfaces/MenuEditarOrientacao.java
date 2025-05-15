package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class MenuEditarOrientacao implements Menu{
	
	private final OrientacaoDto orientacaoDto;
	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;
	
	public MenuEditarOrientacao(OrientacaoDto orientacaoDto, IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService) {
		this.orientacaoDto = orientacaoDto;
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
	 }

	@Override
	public Menu chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		var orientacaoAlterada = idiomaImplementacao.mostrarMenuEditarOrientacao(orientacaoDto, input);
		
		try {
			String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
			
			orientacaoService.atualizarOrientacao(orientacaoAlterada, idOrientacao);
		
			return MenuFactory.criarMenuResultado(TipoMenu.CERTO, pegarMenuAnterior(menuHistorico, orientacaoAlterada), idiomaImplementacao.pegarMensagemEdicaoConcluida(), idiomaImplementacao);
		} catch (Exception e) {
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, pegarMenuAnterior(menuHistorico, orientacaoAlterada), idiomaImplementacao.pegarMensagemEdicaoFalha(), idiomaImplementacao);
		}
		
	}

	public Menu pegarMenuAnterior(MenuHistorico menuHistorico, OrientacaoDto orientacaoAlterada){
		try{
			return menuHistorico.voltarMenu();
		} catch(Exception e ){
			return MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao);
		}
		

	}


	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}
	
}
