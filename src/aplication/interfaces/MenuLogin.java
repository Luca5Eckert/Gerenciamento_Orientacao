package aplication.interfaces;

import java.util.Scanner;
import Dominio.Usuario;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import service.UsuarioService;
import service.exceptions.usuario.LoginException;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class MenuLogin implements Menu {
	private final IdiomaImplementacao idiomaImplementacao;
	private UsuarioService service;

	public MenuLogin(IdiomaImplementacao idiomaImplementacao, UsuarioService usuarioService) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.service = usuarioService;
	}

	@Override
	public Menu chamarMenu(Scanner input) {
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
