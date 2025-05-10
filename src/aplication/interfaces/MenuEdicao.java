package aplication.interfaces;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import java.util.Scanner;

public class MenuEdicao implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private OrientacaoService orientacaoService;
	private OrientacaoDto orientacaoDto;

	public MenuEdicao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService, OrientacaoDto orientacaoDto) {
		this.idiomaImplementacao = idiomaImplementacao; 
		this.orientacaoService = orientacaoService;
		this.orientacaoDto = orientacaoDto;
	}

	public Menu chamarMenu(Scanner input) {
		Menu menu;

		try {
			idiomaImplementacao.mostrarMenuEditarOrientacao(orientacaoDto, input);
			this.orientacaoService.atualizarOrientacao(orientacaoDto, null);
			menu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, MenuFactory.criarMenu(TipoMenu.GERAL),
					idiomaImplementacao.pegarMensagemEdicaoConcluida());
		} catch (Exception e) {
			menu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, MenuFactory.criarMenu(TipoMenu.GERAL),
					e.getMessage());
		}

		return menu;
	}
	
	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}


}
