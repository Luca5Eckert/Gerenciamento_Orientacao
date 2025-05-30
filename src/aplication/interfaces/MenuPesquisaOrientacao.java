package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;
import service.filtros.GerenciadorFiltrosOrientacao;

public class MenuPesquisaOrientacao extends Menu {

	private GerenciadorFiltrosOrientacao gerenciadorFiltro;
	private SessaoUsuario sessaoUsuario;

	public MenuPesquisaOrientacao(IdiomaImplementacao idiomaImplementacao,
			GerenciadorFiltrosOrientacao gerenciadorFiltro, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.gerenciadorFiltro = gerenciadorFiltro;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcaoEscolhida = idiomaImplementacao.mostrarMenuPesquisaOrientacao(input);

		devolverOpcaoEscolhida(opcaoEscolhida, menuHistorico);
	}

	public void definirPalavraPesquisa(String palavraPesquisada) {
		gerenciadorFiltro.setPalavraBuscada(palavraPesquisada);
	}

	public void devolverOpcaoEscolhida(String opcaoEscolhida, MenuHistorico menuHistorico) {
		switch (opcaoEscolhida.toUpperCase()) {
		case "S":
			menuHistorico.voltarMenu();
		default:
			definirPalavraPesquisa(opcaoEscolhida);
			menuHistorico.voltarMenu(MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao, sessaoUsuario));
		}

	}


}
