package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public class MenuPesquisaOrientacao implements Menu {
	private GerenciadorFiltrosOrientacao gerenciadorFiltro;

	public MenuPesquisaOrientacao(GerenciadorFiltrosOrientacao gerenciadorFiltro) {
		this.gerenciadorFiltro = gerenciadorFiltro;
	}

	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuPesquisaOrientacao(input);

		return devolverOpcaoEscolhida(opcaoEscolhida);
	}

	public void definirPalavraPesquisa(String palavraPesquisada) {
		gerenciadorFiltro.setPalavraBuscada(palavraPesquisada);
	}

	public Menu devolverOpcaoEscolhida(String opcaoEscolhida) {
		switch (opcaoEscolhida.toUpperCase()) {
		case "S":
			return MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro);
		default:
			definirPalavraPesquisa(opcaoEscolhida);
			return MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro);
		}

	}

}
