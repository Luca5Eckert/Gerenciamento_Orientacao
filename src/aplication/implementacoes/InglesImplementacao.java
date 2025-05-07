package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public class InglesImplementacao implements IdiomaImplementacao {

	@Override
	public IdiomaOrientacao obterIdiomaOrientacao() {
		return IdiomaOrientacao.PORTUGUES;
	}

	@Override
	public String mostrarMenuInicial(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                          START                             ");
		System.out.println("============================================================");
		System.out.println(" Welcome to the Orientation Management System:              ");
		System.out.println(" 1- Login");
		System.out.println(" 2- Register");
		System.out.println(" 3- Exit system");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                          LOGIN                             ");
		System.out.println("============================================================");
		System.out.println(" Enter your details:\n");
		System.out.println(" 1- Name:");
		String nome = input.nextLine();

		System.out.println(" 2- Password:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, senha);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        REGISTER                            ");
		System.out.println("============================================================");
		System.out.println(" Enter your details:\n");

		System.out.println(" 1- Name:");
		String nome = input.nextLine();

		System.out.println(" 2- Password:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, senha);
	}

	@Override
	public String mostrarMenuGeral(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      MANAGER                               ");
		System.out.println("============================================================");
		System.out.println(" 1- Access Orientations");
		System.out.println(" 2- Logout");
		System.out.println(" 3- Exit system");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) {
		int numeroRepetirVezes = 1;
		List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

		System.out.println("============================================================");
		System.out.println("                       CREATION                             ");
		System.out.println("============================================================");

		System.out.println(" Do you want to create for only your language or for all?");
		System.out.println(" 1- Only for Portuguese");
		System.out.println(" 2- For all");
		String opcao = input.nextLine();

		switch(opcao) {
		case "1":
			numeroRepetirVezes = 1;
		case "2":
			numeroRepetirVezes = IdiomaOrientacao.values().length;
		}

		for(int i = 0; i < numeroRepetirVezes; i++) {
			IdiomaOrientacao idiomaOrientacao = IdiomaOrientacao.pegarIdioma(i);
			String idiomaNome = pegarNomeIdioma(idiomaOrientacao);

			System.out.println(" Orientation title in " + idiomaNome + " :" );
			String tituloOrientacao = input.nextLine();

			System.out.println(" Orientation type in " + idiomaNome + " :");
			System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
			TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
			input.nextLine(); // consume leftover newline

			System.out.println(" Content in " + idiomaNome + " :");
			String conteudo = input.nextLine();

			System.out.println("------------------------------------------------------------");

			listaOrientacaoDto.add(new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao));
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
			System.out.println("                         EDITING                            ");
			System.out.println("============================================================");
			System.out.println(" Select what you want to change:\n");

			System.out.println(" 1- Orientation title");
			System.out.println(" 2- Orientation type");
			System.out.println(" 3- Orientation content");
			System.out.println(" 0- Exit editing");
			System.out.println("------------------------------------------------------------");

			String numeroRetorno = input.nextLine();

			switch (numeroRetorno) {
				case "1":
					System.out.println("------------------------------------------------------------");
					System.out.print(" New title: ");
					titulo = input.nextLine();
					System.out.println("------------------------------------------------------------");
					break;
				case "2":
					System.out.println("------------------------------------------------------------");
					System.out.println(" New orientation type:");
					System.out.println(TipoOrientacao.mostrarTodasTipos(obterIdiomaOrientacao()));
					System.out.print(" Enter the corresponding number: ");
					String tipoStr = input.nextLine();
					try {
						int tipoOrientacaoOpcao = Integer.parseInt(tipoStr);
						tipoOrientacao = TipoOrientacao.pegarOrientacao(tipoOrientacaoOpcao);
					} catch (NumberFormatException e) {
						System.out.println(" Invalid input! Use a number.");
					}
					System.out.println("------------------------------------------------------------");
					break;
				case "3":
					System.out.println("------------------------------------------------------------");
					System.out.print(" New content: ");
					conteudo = input.nextLine();
					System.out.println("------------------------------------------------------------");
					break;
				case "0":
					edicaoMenu = false;
					break;
				default:
					System.out.println(" Invalid option.");
			}
		} while (edicaoMenu);

		return new OrientacaoDto(titulo, tipoOrientacao, conteudo, obterIdiomaOrientacao());
	}

	@Override
	public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palavraPesquisada) {
		System.out.println("============================================================");
		System.out.println("                    ORIENTATIONS                            ");
		System.out.println("============================================================");
		System.out.println(" S- Return to general menu");
		System.out.println(" F- Filters                                    P- Search");

		System.out.println(" A- Clear search");
		System.out.println(" R- Remove filters \n ");
		System.out.println("\n Result: " + palavraPesquisada);

		System.out.println(listaFormatada);

		System.out.println("============================================================");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuOpcoesOrientacao(Scanner input) {
		return null;
	}

	@Override
	public String mostrarMenuAcerto(Scanner input, String mensagemAcerto) {
		System.out.println("------------------------------------------------------------");
		System.out.println(mensagemAcerto);
		System.out.println(" 1 - Continue ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuErro(Scanner input, String mensagemErro) {
		System.out.println("------------------------------------------------------------");
		System.out.println("                           ERROR                            ");
		System.out.println(mensagemErro);
		System.out.println(" 1 - Continue ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	// Mensagens de erro e sucesso podem ser mantidas em português se forem retornadas,
	// mas se desejar também posso traduzi-las.

	@Override
	public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao) {
		System.out.println("============================================================");
		System.out.println("                    ORIENTATION                             ");
		System.out.println("============================================================");
		System.out.println(" " + orientacao.titulo());
		System.out.println(" " + orientacao.tipoOrientacao());
		System.out.println("\n Content:");
		System.out.println(" " + orientacao.conteudo());
		System.out.println("\n  1 - Exit                               2- Edit       ");
		System.out.println("============================================================");

		return input.nextLine();
	}

	@Override
	public String mostrarMenuFiltro(Scanner input, GerenciadorFiltrosOrientacao gerenciador) {
		String opcaoEscolhida;

		System.out.println("============================================================");
		System.out.println("                         FILTERS                            ");
		System.out.println("============================================================");
		System.out.println(" 1 - Filter by Language");
		System.out.println(" 2 - Filter by Orientation Type\n");
		System.out.println(" 3 - View filters");
		System.out.println(" 4 - Apply Selected Filters");
		System.out.println("============================================================");

		opcaoEscolhida = input.nextLine();

		switch (opcaoEscolhida) {
			case "1":
				System.out.println("\nChoose languages to filter:");
				System.out.println("P - Portuguese");
				System.out.println("I - English");
				System.out.println("E - Spanish");
				System.out.println("A - German");
				opcaoEscolhida = input.nextLine();
				break;

			case "2":
				System.out.println("\nChoose orientation types to filter:");
				System.out.println("M - Operation Manual");
				System.out.println("S - Safety Procedures");
				System.out.println("R - Maintenance and Repairs");
				System.out.println("D - Tests and Diagnostics");
				System.out.println("C - Code of Conduct and Sectorial Operations");
				opcaoEscolhida = input.nextLine();
				break;
			case "3":
				System.out.println("------------------------------------------------------------");
				System.out.println(gerenciador.formatarFiltrosAtivados());
				System.out.println("------------------------------------------------------------");
				System.out.println(" 1- Back");
				String opcao = input.nextLine();

				switch (opcao) {
					case "2":
						System.out.println("Enter filter number: ");
						return input.nextLine();
				}
		}

		return opcaoEscolhida;
	}

	@Override
	public String mostrarMenuPesquisaOrientacao(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                       SEARCH                               ");
		System.out.println("============================================================");
		System.out.println(" 1- Exit ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Enter your search: ");
		String pesquisa = input.nextLine();
		System.out.println("============================================================");

		return pesquisa;
	}

	@Override
	public String pegarMensagemEdicaoConcluida() {
		return "Edition completed successfully";
	}

	@Override
	public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao){
		return switch(idiomaOrientacao) {
			case PORTUGUES -> "Portuguese";
			case INGLES -> "English";
			case ESPANHOL -> "Spanish";
			case ALEMAO -> "German";
			default -> "Other";
		};
	}

	@Override
	public String pegarMensagemErroLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemErroLoginUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemErroLoginSenha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemLoginConcluido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemErroCadastroUsuarioExistente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemErroCadastroSenhaPequena() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemMaiscula() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemCaracterEspecial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pegarMensagemCadastroConcluido() {
		// TODO Auto-generated method stub
		return null;
	}

}
