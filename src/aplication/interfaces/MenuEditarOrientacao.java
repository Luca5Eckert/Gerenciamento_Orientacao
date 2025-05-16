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
	public void chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		var orientacaoAlterada = idiomaImplementacao.mostrarMenuEditarOrientacao(orientacaoDto, input);
		Menu proximoMenu = null;
		
		try {
			String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
			
			orientacaoService.atualizarOrientacao(orientacaoAlterada, idOrientacao);
		
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, pegarMenuAnterior(menuHistorico, orientacaoAlterada), idiomaImplementacao.pegarMensagemEdicaoConcluida(), idiomaImplementacao);
		} catch (Exception e) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, pegarMenuAnterior(menuHistorico, orientacaoAlterada), idiomaImplementacao.pegarMensagemEdicaoFalha(), idiomaImplementacao);
		} finally {
			menuHistorico.definirProximoMenu(proximoMenu);
		}
				
	}

	public Menu pegarMenuAnterior(MenuHistorico menuHistorico, OrientacaoDto orientacaoAlterada){
		try{
			return menuHistorico.voltarMenu();
		} catch(Exception e ){
			return MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao);
		}
		

	}

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		
	}
}
