package aplication.implementacoes;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;

public class AlemaoImplementacao implements IdiomaImplementacao {

	@Override
	public IdiomaOrientacao obterIdiomaOrientacao() {
		return IdiomaOrientacao.ALEMAO;
	}

	@Override
	public int mostrarMenuInicial(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                          START                             ");
		System.out.println("============================================================");
		System.out.println(" Willkommen im Leitfaden-Management-System:                 ");
		System.out.println(" 1- Anmelden");
		System.out.println(" 2- Registrieren");
		System.out.println(" 3- System verlassen");
		System.out.println("------------------------------------------------------------");

		return input.nextInt();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                          LOGIN                             ");
		System.out.println("============================================================");
		System.out.println(" Geben Sie Ihre Daten ein:\n");
		System.out.println(" 1- Name:");
		String nome = input.nextLine();

		System.out.println(" 2- Passwort:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, senha);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                       REGISTRIEREN                         ");
		System.out.println("============================================================");
		System.out.println(" Geben Sie Ihre Daten ein:\n");

		System.out.println(" 1- Name:");
		String nome = input.nextLine();

		System.out.println(" 2- Passwort:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, senha);
	}

	@Override
	public int mostrarMenuGeral(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                       MANAGER                              ");
		System.out.println("============================================================");

		System.out.println(" 1- Anleitung erstellen");
		System.out.println(" 2- Anleitungen anzeigen");
		System.out.println(" 3- Anleitung suchen");
		System.out.println(" 4- Anleitung löschen");
		System.out.println(" 5- Manager verlassen");

		System.out.println("------------------------------------------------------------");

		return input.nextInt();
	}

	@Override
	public OrientacaoDto mostrarMenuCriarOrientacao(Scanner input) {
		input.nextLine();
		System.out.println("============================================================");
		System.out.println("                         ERSTELLUNG                         ");
		System.out.println("============================================================");

		System.out.println(" Titel der Anleitung:");
		String tituloOrientacao = input.nextLine();

		System.out.println(" Art der Anleitung:");
		System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
		TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());

		System.out.println(" Inhalt:");
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
			System.out.println("                        BEARBEITEN                          ");
			System.out.println("============================================================");
			System.out.println(" Wählen Sie aus, was Sie ändern möchten:\n");

			System.out.println(" 1- Titel der Anleitung");
			System.out.println(" 2- Art der Anleitung");
			System.out.println(" 3- Inhalt der Anleitung");

			System.out.println(" 0- Bearbeitung verlassen");

			System.out.println("------------------------------------------------------------");

			int numeroRetorno = input.nextInt();
			input.nextLine();

			switch (numeroRetorno) {
			case 1:
				System.out.println("------------------------------------------------------------");
				System.out.print(" Neuer Titel: ");
				titulo = input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			case 2:
				System.out.println("------------------------------------------------------------");
				System.out.print(" Neue Art der Anleitung: ");
				tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
				input.nextLine();
				System.out.println("------------------------------------------------------------");
				break;
			case 3:
				System.out.println("------------------------------------------------------------");
				System.out.print(" Neuer Inhalt: ");
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
		System.out.println(" 1 - Weiter ");
		System.out.println("------------------------------------------------------------");
		return input.nextInt();
	}

	@Override
	public int mostrarMenuErro(Scanner input, String mensagemErro) {
		System.out.println("------------------------------------------------------------");
		System.out.println("                           FEHLER                           ");
		System.out.println(mensagemErro);
		System.out.println(" 1 - Weiter ");
		System.out.println("------------------------------------------------------------");
		return input.nextInt();
	}

	@Override
	public String pegarMensagemErroLogin() {
		return "Anmeldung fehlgeschlagen";
	}

	@Override
	public String pegarMensagemErroLoginUsuario() {
		return "Benutzer existiert nicht";
	}

	@Override
	public String pegarMensagemErroLoginSenha() {
		return "Falsches Passwort";
	}

	@Override
	public String pegarMensagemLoginConcluido() {
		return "Anmeldung erfolgreich durchgeführt";
	}

	@Override
	public String pegarMensagemErroCadastroUsuarioExistente() {
	    return "Der Benutzer ist bereits registriert.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaPequena() {
	    return "Das Passwort muss mindestens 8 Zeichen lang sein.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemMaiscula() {
	    return "Das Passwort muss mindestens einen Großbuchstaben enthalten.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemCaracterEspecial() {
	    return "Das Passwort muss mindestens ein Sonderzeichen enthalten.";
	}

	@Override
	public String pegarMensagemCadastroConcluido() {
	    return "Registrierung erfolgreich abgeschlossen!";
	}

}
