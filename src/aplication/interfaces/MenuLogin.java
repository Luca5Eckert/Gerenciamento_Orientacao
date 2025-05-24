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

public class MenuLogin extends Menu {

	private final UsuarioService service;

	public MenuLogin(IdiomaImplementacao idiomaImplementacao, UsuarioService usuarioService) {
		super(idiomaImplementacao);
		this.service = usuarioService;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		UsuarioDto usuarioParaLogar = idiomaImplementacao.mostrarMenuLogin(input);
		Menu proximoMenu = null;

		try {
			service.realizarLogin(usuarioParaLogar);
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO,
					MenuFactory.criarMenu(TipoMenu.GERAL, idiomaImplementacao),
					idiomaImplementacao.pegarMensagemLoginConcluido(), idiomaImplementacao);
		} catch (LoginException e) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA,
					MenuFactory.criarMenu(TipoMenu.INICIO, idiomaImplementacao), e.getMessage(), idiomaImplementacao);
		}

		menuHistorico.definirProximoMenu(proximoMenu);
	}

}
