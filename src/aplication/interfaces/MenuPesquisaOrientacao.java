package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.filtros.GerenciadorFiltrosOrientacao;

public class MenuPesquisaOrientacao extends Menu {

	private GerenciadorFiltrosOrientacao gerenciadorFiltro;

	public MenuPesquisaOrientacao(IdiomaImplementacao idiomaImplementacao,
			GerenciadorFiltrosOrientacao gerenciadorFiltro) {
		super(idiomaImplementacao);
		this.gerenciadorFiltro = gerenciadorFiltro;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuPesquisaOrientacao(input);

		var proximoMenu = devolverOpcaoEscolhida(opcaoEscolhida);

		menuHistorico.definirProximoMenu(proximoMenu);

	}

	public void definirPalavraPesquisa(String palavraPesquisada) {
		gerenciadorFiltro.setPalavraBuscada(palavraPesquisada);
	}

	public Menu devolverOpcaoEscolhida(String opcaoEscolhida) {
		switch (opcaoEscolhida.toUpperCase()) {
		case "S":
			return MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao);
		default:
			definirPalavraPesquisa(opcaoEscolhida);
			return MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao);
		}

	}


}
