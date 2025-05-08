package aplication.interfaces;

import aplication.implementacoes.IdiomaImplementacao;
import java.util.Scanner;

public class MenuFalha implements Menu {
	
	private final IdiomaImplementacao idiomaImplementacao;
	private final Menu proximoMenu;
	private final String mensagemErro;
	
	public MenuFalha(IdiomaImplementacao idiomaImplementacao, Menu proximoMenu, String mensagemErro) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.proximoMenu = proximoMenu;
		this.mensagemErro = mensagemErro;
	}
	
	
	@Override
	public Menu chamarMenu(Scanner input) {
		String opcao;
		opcao = idiomaImplementacao.mostrarMenuErro(input, mensagemErro);
		
		return devolverOpcaoEscolhida(opcao);
	}


	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch(opcao) {
		case "1" -> this.proximoMenu;
		default -> this.proximoMenu;
		};
	}
	

}
