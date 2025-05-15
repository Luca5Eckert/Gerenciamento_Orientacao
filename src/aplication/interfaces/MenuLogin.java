package aplication.interfaces;

import java.util.Scanner;
import Dominio.Usuario;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;
import service.UsuarioService;
import service.exceptions.usuario.LoginException;
import service.exceptions.usuario.LoginSenhaException;
import service.exceptions.usuario.LoginUsuarioException;

public class MenuLogin implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private final UsuarioService service;

	public MenuLogin(IdiomaImplementacao idiomaImplementacao, UsuarioService usuarioService) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.service = usuarioService;
	}

	@Override
	public Menu chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		UsuarioDto usuarioParaLogar = idiomaImplementacao.mostrarMenuLogin(input);
		Menu menu;

		try {
			service.realizarLogin(usuarioParaLogar);
			menu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao),
					idiomaImplementacao.pegarMensagemLoginConcluido(), idiomaImplementacao);
		} catch (LoginException e) {
			menu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, MenuFactory.criarMenu(TipoMenu.INICIO, idiomaImplementacao), e.getMessage(), idiomaImplementacao);
		}

		return menu;
	}

	
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}


}
