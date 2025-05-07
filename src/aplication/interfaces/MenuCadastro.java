package aplication.interfaces;

import Dominio.Usuario;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;

import java.util.Scanner;
import service.UsuarioService;
import service.exceptions.usuario.CadastroException;

public class MenuCadastro implements Menu {
	private UsuarioService usuarioService;

	public MenuCadastro(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
		UsuarioDto usuarioCadastrar = idiomaImplementacao.mostrarMenuCadastro(input);
		Menu menu;

		try {
			usuarioService.realizarCadastro(usuarioCadastrar);
			menu = MenuFactory.criarMenuResultado(TipoMenu.CERTO, MenuFactory.criarMenu(TipoMenu.INICIO),
					idiomaImplementacao.pegarMensagemCadastroConcluido());
		} catch (CadastroException ce) {
			return menu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, MenuFactory.criarMenu(TipoMenu.INICIO), ce.getMessage());
		}
		return menu;

	}

	
}
