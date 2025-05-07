package aplication.implementacoes;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;

public class EspanholImplementacao implements IdiomaImplementacao {

	@Override
	public IdiomaOrientacao obterIdiomaOrientacao() {
		return IdiomaOrientacao.ESPANHOL;
	}

	@Override
	public int mostrarMenuInicial(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                          INICIO                            ");
		System.out.println("============================================================");
		System.out.println(" Bienvenido al sistema de gestión de Orientaciones:         ");
		System.out.println(" 1- Iniciar sesión");
		System.out.println(" 2- Registrarse");
		System.out.println(" 3- Salir del sistema");
		System.out.println("------------------------------------------------------------");

		return input.nextInt();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                        INICIAR SESIÓN                      ");
		System.out.println("============================================================");
		System.out.println(" Ingrese sus datos:\n");
		System.out.println(" 1- Nombre:");
		String nome = input.nextLine();

		System.out.println(" 2- Contraseña:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, senha);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                        REGISTRO                            ");
		System.out.println("============================================================");
		System.out.println(" Ingrese sus datos:\n");

		System.out.println(" 1- Nombre:");
		String nome = input.nextLine();

		System.out.println(" 2- Contraseña:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, senha);
	}

	@Override
	public int mostrarMenuGeral(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                      GESTOR                                ");
		System.out.println("============================================================");

		System.out.println(" 1- Crear Orientación");
		System.out.println(" 2- Ver Orientaciones");
		System.out.println(" 3- Buscar Orientación");
		System.out.println(" 4- Eliminar Orientación");
		System.out.println(" 5- Salir del Gestor");

		System.out.println("------------------------------------------------------------");

		return input.nextInt();
	}

	@Override
	public OrientacaoDto mostrarMenuCriarOrientacao(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                        CREACIÓN                            ");
		System.out.println("============================================================");

		System.out.println(" Título de la orientación:");
		String tituloOrientacao = input.nextLine();

		System.out.println(" Tipo de orientación:");
		System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
		TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());

		System.out.println(" Contenido:");
		String conteudo = input.nextLine();

		System.out.println("------------------------------------------------------------");

		return new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, this.obterIdiomaOrientacao());
	}

	@Override
	public OrientacaoDto mostrarMenuEditarOrientacao(OrientacaoDto orientacaoDto, Scanner input) {
		boolean edicaoMenu = true;
		String titulo = orientacaoDto.titulo();
		String conteudo = orientacaoDto.conteudo();
		TipoOrientacao tipoOrientacao = orientacaoDto.tipoOrientacao();

		do {
			System.out.println("============================================================");
			System.out.println("                         EDICIÓN                            ");
			System.out.println("============================================================");
			System.out.println(" Seleccione qué desea modificar:\n");

			System.out.println(" 1- Título de la orientación");
			System.out.println(" 2- Tipo de orientación");
			System.out.println(" 3- Contenido de la orientación");

			System.out.println(" 0- Salir de la edición");

			System.out.println("------------------------------------------------------------");

			int numeroRetorno = input.nextInt();
			input.nextLine();

			switch (numeroRetorno) {
			case 1:
				System.out.println("------------------------------------------------------------");
				System.out.print(" Nuevo título: ");
				titulo = input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			case 2:
				System.out.println("------------------------------------------------------------");
				System.out.print(" Nuevo tipo de orientación: ");
				tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
				input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			case 3:
				System.out.println("------------------------------------------------------------");
				System.out.print(" Nuevo contenido: ");
				conteudo = input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			default:
				edicaoMenu = false;
			}

		} while (edicaoMenu);

		return new OrientacaoDto(titulo, tipoOrientacao, conteudo, obterIdiomaOrientacao());
	}

	@Override
	public OrientacaoDto mostrarMenuOrientacaoDisponiveis(Scanner input) {
		return null;
	}

	@Override
	public int mostrarMenuOpcoesOrientacao(Scanner input) {
		return 0;
	}

	@Override
	public int mostrarMenuAcerto(Scanner input, String mensagemAcerto) {
		System.out.println("------------------------------------------------------------");
		System.out.println(mensagemAcerto);
		System.out.println(" 1 - Continuar ");
		System.out.println("------------------------------------------------------------");
		return input.nextInt();
	}

	@Override
	public int mostrarMenuErro(Scanner input, String mensagemErro) {
		System.out.println("------------------------------------------------------------");
		System.out.println("                            ERROR                           ");
		System.out.println(mensagemErro);
		System.out.println(" 1 - Continuar ");
		System.out.println("------------------------------------------------------------");
		return input.nextInt();
	}

	@Override
	public String pegarMensagemErroLogin() {
		return "No fue posible iniciar sesión";
	}

	@Override
	public String pegarMensagemErroLoginUsuario() {
		return "Usuario no existente";
	}

	@Override
	public String pegarMensagemErroLoginSenha() {
		return "Contraseña incorrecta";
	}

	@Override
	public String pegarMensagemLoginConcluido() {
		return "Inicio de sesión realizado con éxito";
	}
	
	@Override
	public String pegarMensagemErroCadastroUsuarioExistente() {
	    return "El usuario ya está registrado.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaPequena() {
	    return "La contraseña debe tener al menos 8 caracteres.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemMaiscula() {
	    return "La contraseña debe contener al menos una letra mayúscula.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemCaracterEspecial() {
	    return "La contraseña debe contener al menos un carácter especial.";
	}

	@Override
	public String pegarMensagemCadastroConcluido() {
	    return "¡Registro completado con éxito!";
	}


}
