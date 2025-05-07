 package aplication.interfaces.teste;

import aplication.MenuFactory;
import aplication.implementacoes.PortuguesImplementacao;
import aplication.interfaces.MenuExibirOrientacoes;
import service.formatacao.FormatacaoListaOrientacao;
import aplication.interfaces.Menu;

public class TesteFacotry {
	public static void main(String[] args) {
		Menu menu = new MenuExibirOrientacoes(MenuFactory.criarOrientacaoService(), MenuFactory.criarGerenciadorFiltro(new PortuguesImplementacao()), new FormatacaoListaOrientacao());
	
		System.out.println(menu);
		
	}
}
