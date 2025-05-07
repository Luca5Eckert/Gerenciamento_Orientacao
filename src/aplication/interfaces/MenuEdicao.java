package aplication.interfaces;

import Dominio.Orientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import java.util.Scanner;

public class MenuEdicao implements Menu {
	private OrientacaoService service;
	private OrientacaoDto orientacaoDto;

	public MenuEdicao(OrientacaoDto orientacaoDto) {
		this.service = new OrientacaoService();
		this.orientacaoDto = orientacaoDto;
	}

	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		int opcao = 1;
		Menu menu;

		try {
			idiomaImplementacao.mostrarMenuEditarOrientacao(orientacaoDto, input);
			this.service.atualizarOrientacao(orientacaoDto, null);
			menu = MenuFactory.criarMenuResultado(
				TipoMenu.CERTO,
				MenuFactory.criarMenu(TipoMenu.GERAL),
				idiomaImplementacao.pegarMensagemEdicaoConcluida()
			);
		} catch (Exception e) {
			menu = MenuFactory.criarMenuResultado(
				TipoMenu.FALHA,
				MenuFactory.criarMenu(TipoMenu.GERAL),
				e.getMessage()
			);
		}

		return menu;
	}


	

}
