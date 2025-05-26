package aplication.interfaces;

import java.util.Scanner;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import service.UsuarioService;
import service.exceptions.usuario.LoginException;

public class MenuLogin extends Menu {

	private final UsuarioService service;

	public MenuLogin(IdiomaImplementacao idiomaImplementacao, UsuarioService usuarioService) {
		super(idiomaImplementacao);
		this.service = usuarioService;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		UsuarioDto usuarioParaLogar = idiomaImplementacao.mostrarMenuLogin(input);

		try {
			service.realizarLogin(usuarioParaLogar);
			var proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO,
					MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao),
					idiomaImplementacao.pegarMensagemLoginConcluido(), idiomaImplementacao);
			menuHistorico.definirProximoMenu(proximoMenu);
		} catch (LoginException e) {
			menuHistorico.voltarMenu(MenuFactory.criarMenuResultado(TipoMenu.FALHA,
					menuHistorico.pegarMenuAnterior(), e.getMessage(), idiomaImplementacao));
		}

	}

}
