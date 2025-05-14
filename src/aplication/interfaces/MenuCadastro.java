package aplication.interfaces;

import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.UsuarioDto;

import java.util.Scanner;
import service.UsuarioService;
import service.exceptions.usuario.CadastroException;

public class MenuCadastro implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private UsuarioService usuarioService;

	public MenuCadastro(IdiomaImplementacao idiomaImplementacao, UsuarioService usuarioService) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.usuarioService = usuarioService;
	}

	@Override
	public Menu chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
		UsuarioDto usuarioCadastrar = idiomaImplementacao.mostrarMenuCadastro(input);

		try {
			usuarioService.realizarCadastro(usuarioCadastrar);
			return MenuFactory.criarMenuResultado(TipoMenu.CERTO, MenuFactory.criarMenu(TipoMenu.INICIO),
					idiomaImplementacao.pegarMensagemCadastroConcluido());
		} catch (CadastroException ce) {
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, MenuFactory.criarMenu(TipoMenu.INICIO),
					ce.getMessage());
		}

	}
	
	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
