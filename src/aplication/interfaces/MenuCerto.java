package aplication.interfaces;

import aplication.implementacoes.IdiomaImplementacao;
import java.util.Scanner;

public class MenuCerto implements Menu {
	private Menu proximoMenu;
	private String mensagemAcerto;

	public MenuCerto(Menu proximoMenu, String mensagemAcerto) {
		this.proximoMenu = proximoMenu;
		this.mensagemAcerto = mensagemAcerto;
	}

	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		String opcao = idiomaImplementacao.mostrarMenuAcerto(input, mensagemAcerto);

		return devolverOpcaoEscolhida(opcao);
	}

	public Menu devolverOpcaoEscolhida(String opcao) {
		return switch (opcao) {
		case "1" -> this.proximoMenu;
		default -> this.proximoMenu;
		};
	}

}
