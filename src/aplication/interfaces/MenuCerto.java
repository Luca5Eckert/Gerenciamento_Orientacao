package aplication.interfaces;

import aplication.implementacoes.IdiomaImplementacao;
import java.util.Scanner;

public class MenuCerto  {
	private IdiomaImplementacao idiomaImplementacao;
	private Menu proximoMenu;
	private String mensagemAcerto;

	public MenuCerto(IdiomaImplementacao idiomaImplementacao, Menu proximoMenu, String mensagemAcerto) {
		this.proximoMenu = proximoMenu;
		this.mensagemAcerto = mensagemAcerto;
	}

	@Override
	public Menu chamarMenu( Scanner input) {
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
