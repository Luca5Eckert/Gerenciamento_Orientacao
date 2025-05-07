package aplication.interfaces;

import java.lang.System.LoggerFinder;
import java.util.Scanner;

import Dominio.Usuario;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import service.SessaoUsuario;
import service.UsuarioService;
import service.exceptions.usuario.LoginException;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class MenuLogin implements Menu {
	private UsuarioService service;

	public MenuLogin(UsuarioService usuarioService) {
		this.service = usuarioService;
	}

	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		UsuarioDto usuarioParaLogar = idiomaImplementacao.mostrarMenuLogin(input);
		Menu menu;

		try {
			service.realizarLogin(usuarioParaLogar);
			menu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, MenuFactory.criarMenu(TipoMenu.GERAL),
					idiomaImplementacao.pegarMensagemLoginConcluido());
		} catch (LoginException e) {
			menu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, MenuFactory.criarMenu(TipoMenu.INICIO), e.getMessage());
		}

		return menu;
	}


}
