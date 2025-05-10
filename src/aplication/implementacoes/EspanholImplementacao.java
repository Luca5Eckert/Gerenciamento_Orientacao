package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public class EspanholImplementacao implements IdiomaImplementacao {

	@Override
	public IdiomaOrientacao obterIdiomaOrientacao() {
		return IdiomaOrientacao.PORTUGUES;
	}

	@Override
	public String mostrarMenuInicial(Scanner input) {

		System.out.println("============================================================");
		System.out.println("                        INICIO                              ");
		System.out.println("============================================================");
		System.out.println(" Bienvenido al sistema de gestor de Orientaciones:          ");
		System.out.println(" 1- Iniciar sesión");
		System.out.println(" 2- Registrarse");
		System.out.println(" 3- Cambiar idioma");
		System.out.println(" 4- Salir del sistema");
		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        INICIAR SESIÓN                      ");
		System.out.println("============================================================");
		System.out.println(" Ingrese sus datos:\n");

		System.out.print(" Email:");
		String email = input.nextLine();

		System.out.print(" Contraseña:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(email, null, senha);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      REGISTRO                              ");
		System.out.println("============================================================");
		System.out.println(" Ingrese sus datos:\n");

		System.out.print(" Nombre:");
		String nome = input.nextLine();

		System.out.print(" Email:");
		String email = input.nextLine();

		System.out.print(" Contraseña:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, email, senha);
	}

	@Override
	public String mostrarMenuGeral(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                     GESTOR                                 ");
		System.out.println("============================================================");

		System.out.println(" 0- Crear Orientación");
		System.out.println(" 1- Acceder a Orientaciones");
		System.out.println(" 2- Cerrar sesión");
		System.out.println(" 3- Salir del sistema");
		System.out.println(" 4- Cambiar idioma");

		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
		int numeroRepetirVezes = 1;
		List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

		System.out.println("============================================================");
		System.out.println("                       CREACIÓN                             ");
		System.out.println("============================================================");

		System.out.println(" S- Salir ");
		System.out.println(" ¿Desea crear solo para su idioma o para todos? ");
		System.out.println(" 1- Solo para Portugués ");
		System.out.println(" 2- Para todos ");
		String opcao = input.nextLine();

		switch (opcao.toUpperCase()) {
		case "1":
		    numeroRepetirVezes = 1;
		    break;
		case "2":
		    numeroRepetirVezes = IdiomaOrientacao.values().length;
		    break;
		case "S":
		    throw new Exception();
		}

		System.out.println(" Tipo de Orientación:");
		System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
		TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
		input.nextLine();

		for (int i = 0; i < numeroRepetirVezes; i++) {
			IdiomaOrientacao idiomaOrientacao = IdiomaOrientacao.pegarIdioma(i);
			String idiomaNome = pegarNomeIdioma(idiomaOrientacao);

			System.out.println(" Título de orientación en " + idiomaNome + " :");
			String tituloOrientacao = input.nextLine();

			System.out.println(" Contenido en " + idiomaNome + " :");
			String conteudo = input.nextLine();

			System.out.println("------------------------------------------------------------");

			listaOrientacaoDto
					.add(new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao));
		}

		return listaOrientacaoDto;
	}

	@Override
	public OrientacaoDto mostrarMenuEditarOrientacao(OrientacaoDto orientacaoDto, Scanner input) {
		boolean edicaoMenu = true;
		String titulo = orientacaoDto.titulo();
		String conteudo = orientacaoDto.conteudo();
		TipoOrientacao tipoOrientacao = orientacaoDto.tipoOrientacao();

		do {
			System.out.println("============================================================");
			System.out.println("                        EDICIÓN                             ");
			System.out.println("============================================================");
			System.out.println(" Seleccione lo que desea cambiar: \n");

			System.out.println(" 1- Título de la orientación");
			System.out.println(" 2- Tipo de Orientación");
			System.out.println(" 3- Contenido de Orientación");
			System.out.println(" 0- Salir de edición");
			System.out.println("------------------------------------------------------------");

			String numeroRetorno = input.nextLine();

			switch (numeroRetorno) {
			case "1":
				System.out.println("------------------------------------------------------------");
				System.out.print(" Nuevo título: ");
				titulo = input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			case "2":
				System.out.println("------------------------------------------------------------");
				System.out.println(" Nuevo tipo de orientación:");
				System.out.println(TipoOrientacao.mostrarTodasTipos(obterIdiomaOrientacao()));
				System.out.print(" Digite el número correspondiente: ");
				String tipoStr = input.nextLine();
				try {
					int tipoOrientacaoOpcao = Integer.parseInt(tipoStr);
					tipoOrientacao = TipoOrientacao.pegarOrientacao(tipoOrientacaoOpcao);
				} catch (NumberFormatException e) {
					System.out.println(" ¡Entrada inválida! Use un número.");
				}
				System.out.println("------------------------------------------------------------");
				break;
			case "3":
				System.out.println("------------------------------------------------------------");
				System.out.print(" Nuevo contenido: ");
				conteudo = input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			case "0":
				edicaoMenu = false;
				break;
			default:
				System.out.println(" Opción inválida.");
			}

		} while (edicaoMenu);

		return new OrientacaoDto(titulo, tipoOrientacao, conteudo, obterIdiomaOrientacao());
	}

	@Override
	public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palabraPesquisada) {
		System.out.println("============================================================");
		System.out.println("                   ORIENTACIONES                            ");
		System.out.println("============================================================");
		System.out.println(" S- Salir al menú general");
		System.out.println(" F- Filtros                                   P- Buscar     ");

		System.out.println(" A- Borrar búsqueda");
		System.out.println(" R- Quitar filtros  \n ");
		System.out.println("\n Resultado: " + palabraPesquisada);

		System.out.println(listaFormatada);

		System.out.println("============================================================");
		String orientecaoSelecionada = input.nextLine();

		return orientecaoSelecionada;
	}

	@Override
	public String mostrarMenuOpcoesOrientacao(Scanner input) {
		return null;
	}

	@Override
	public String mostrarMenuAcerto(Scanner input, String mensagemAcerto) {
		System.out.println("------------------------------------------------------------");
		System.out.println(mensagemAcerto);
		System.out.println(" 1 - Continuar ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuErro(Scanner input, String mensagemErro) {
		System.out.println("------------------------------------------------------------");
		System.out.println("                           ERROR                            ");
		System.out.println(mensagemErro);
		System.out.println(" 1 - Continuar ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemErroLogin() {
		return "No se pudo realizar el inicio de sesión";
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
		return "Inicio de sesión completado con éxito";
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
		return "¡Registro realizado con éxito!";
	}

	@Override
	public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientacoes) {
		System.out.println("============================================================");
		System.out.println("                    ORIENTACIÓN                             ");
		System.out.println("============================================================");
		System.out.println(" Título: " + orientacao.titulo());
		System.out.println(" Tipo: " + orientacao.tipoOrientacao());
		System.out.println("\n Contenido:");
		System.out.println(" " + orientacao.conteudo());
		System.out.println("\n  S - Salir         E- Editar             B- Borrar       ");
		System.out.println(" En otros idiomas: ");
		System.out.println(idiomasOrientacoes);
		System.out.println("============================================================");

		return input.nextLine();

	}

	@Override
	public String mostrarMenuFiltro(Scanner input, GerenciadorFiltrosOrientacao gerenciador) {
		String opcaoEscolhida;

		System.out.println("============================================================");
		System.out.println("                       FILTROS                             ");
		System.out.println("============================================================");
		System.out.println(" 1 - Filtrar por Idioma");
		System.out.println(" 2 - Filtrar por Tipo de Orientación\n");
		System.out.println(" 3 - Ver los filtros");
		System.out.println(" 4 - Aplicar Filtros Seleccionados");
		System.out.println("============================================================");

		opcaoEscolhida = input.nextLine();

		switch (opcaoEscolhida) {
		case "1":
			System.out.println("\nEscoja los idiomas para filtrar:");
			System.out.println("P - Portugués");
			System.out.println("I - Inglés");
			System.out.println("E - Español");
			System.out.println("A - Alemán");
			opcaoEscolhida = input.nextLine();
			break;

		case "2":
			System.out.println("\nEscoja los tipos de orientación para filtrar:");
			System.out.println("M - Manual de Operación");
			System.out.println("S - Procedimientos de Seguridad");
			System.out.println("R - Mantenimiento y Reparaciones");
			System.out.println("D - Pruebas y Diagnóstico");
			System.out.println("C - Manual de Conducta y Operaciones Sectoriales");
			opcaoEscolhida = input.nextLine();
			break;
		case "3":

			System.out.println("------------------------------------------------------------");
			System.out.println(gerenciador.formatarFiltrosAtivados());
			System.out.println("------------------------------------------------------------");
			System.out.println(" V- Volver");
			String opcao = input.nextLine();

		}

		return opcaoEscolhida;
	}

	@Override
	public String mostrarMenuPesquisaOrientacao(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      Búsqueda                              ");
		System.out.println("============================================================");
		System.out.println(" 1- Salir ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Digite su búsqueda: ");
		String pesquisa = input.nextLine();
		System.out.println("============================================================");

		return pesquisa;

	}

	@Override
	public String pegarMensagemEdicaoConcluida() {
		return "Edición realizada con éxito";
	}

	@Override
	public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao) {
		return switch (idiomaOrientacao) {
		case PORTUGUES -> "Portugués";
		case INGLES -> "Inglés";
		case ESPANHOL -> "Español";
		case ALEMAO -> "Alemán";
		default -> "Otro";
		};
	}

	@Override
	public String pegarMensangemAdicaoConcluida() {
		return " Adición realizada con éxito";
	}

	@Override
	public String pegarMensangemAdicaoFalhada() {
		return " Fallo al añadir orientación";
	}

	@Override
	public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao) {
		return null;
	}

	@Override
	public String pegarFiltroIdioma() {
		return "Filtros de idiomas: ";
	}

	@Override
	public String pegarFiltroTipo() {
		return "Filtros de tipos: ";
	}

	@Override
	public String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    CAMBIAR IDIOMA                          ");
		System.out.println("============================================================");
		System.out.println(" S- Salir\n");
		System.out.println(" Idiomas disponibles: ");
		System.out.println(idiomaFormatados);
		System.out.println("============================================================");
		
		return input.nextLine();
	}
	
	@Override
	public String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado) {
		return " Idioma cambiado a " + idiomaAlterado;
	}
}