package aplication.interfaces;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;

import java.util.Scanner;
import service.UsuarioService;
import service.exceptions.usuario.CadastroException;

public class MenuCadastro extends Menu {

	private UsuarioService usuarioService;

	public MenuCadastro(IdiomaImplementacao idiomaImplementacao, UsuarioService usuarioService) {
		super(idiomaImplementacao);
		this.usuarioService = usuarioService;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		Menu proximoMenu = null;
		UsuarioDto usuarioCadastrar = idiomaImplementacao.mostrarMenuCadastro(input);

		try {
			usuarioService.realizarCadastro(usuarioCadastrar);
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuHistorico.voltarMenu(),
					idiomaImplementacao.pegarMensagemCadastroConcluido(), idiomaImplementacao);
		} catch (CadastroException ce) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.voltarMenu(), ce.getMessage(),
					idiomaImplementacao);
		} finally {
			menuHistorico.definirProximoMenu(proximoMenu);
		}

	}



}
