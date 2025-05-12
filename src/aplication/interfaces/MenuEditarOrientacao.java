package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class MenuEditarOrientacao implements Menu{
	
	private Menu menuAnterior;
	private final OrientacaoDto orientacaoDto;
	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;
	
	public MenuEditarOrientacao(Menu menuAnterior ,OrientacaoDto orientacaoDto, IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService) {
		this.menuAnterior = menuAnterior; 
		this.orientacaoDto = orientacaoDto;
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
	 }

	@Override
	public Menu chamarMenu(Scanner input) {
		var OrientacaoAlterada = idiomaImplementacao.mostrarMenuEditarOrientacao(orientacaoDto, input);
		
		try {
			String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
			
			orientacaoService.atualizarOrientacao(OrientacaoAlterada, idOrientacao);
			
			return MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuAnterior, idiomaImplementacao.pegarMensagemEdicaoConcluida());
		} catch (Exception e) {
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuAnterior, idiomaImplementacao.pegarMensagemEdicaoFalha());
		}
		
	}

	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}
	
}
