package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public class PortuguesImplementacao implements IdiomaImplementacao {

	@Override
	public IdiomaOrientacao obterIdiomaOrientacao() {
		return IdiomaOrientacao.PORTUGUES;
	}

	@Override
	public String mostrarMenuInicial(Scanner input) {

		System.out.println("============================================================");
		System.out.println("                        INICIO                              ");
		System.out.println("============================================================");
		System.out.println(" Bem vindo ao sistema de gerenciador de Orientações:        ");
		System.out.println(" 1- Login");
		System.out.println(" 2- Cadastro");
		System.out.println(" 3- Sair sistema");
		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        LOGIN                               ");
		System.out.println("============================================================");
		System.out.println(" Insira seus dados:\n");
		System.out.println(" 1- Nome:");
		String nome = input.nextLine();

		System.out.println(" 2- Senha:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(0, nome, senha);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      CADASTRO                              ");
		System.out.println("============================================================");
		System.out.println(" Insira seus dados:\n");

		System.out.println(" 1- Nome:");
		String nome = input.nextLine();

		System.out.println(" 2- Senha:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(0, nome, senha);
	}

	@Override
	public String mostrarMenuGeral(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                     GERENCIADOR                            ");
		System.out.println("============================================================");

		System.out.println(" 0- Criar Orientação");
		System.out.println(" 1- Acessar Orientações");
		System.out.println(" 2- Sair da conta");
		System.out.println(" 3- Sair sistema");

		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) {
		int numeroRepetirVezes = 1;
		List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();
		
		System.out.println("============================================================");
		System.out.println("                       CRIAÇÃO                              ");
		System.out.println("============================================================");
		
		System.out.println(" Deseja criar para só seu idioma ou para todos? ");
		System.out.println(" 1- Apenas para Português " );
		System.out.println(" 2- Para todos ");
		String opcao = input.nextLine();
		
		switch(opcao) {
		case "1":
			numeroRepetirVezes = 1;
			break;
		case "2":
			numeroRepetirVezes = IdiomaOrientacao.values().length;
			break;
		}

		System.out.println(" Tipo Orientação:");
		System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
		TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
		input.nextLine();
		
		for(int i = 0; i < numeroRepetirVezes; i++) {
			IdiomaOrientacao idiomaOrientacao = IdiomaOrientacao.pegarIdioma(i);
			String idiomaNome = pegarNomeIdioma(idiomaOrientacao);
			
			System.out.println(" Título orientação em " + idiomaNome + " :" );
			String tituloOrientacao = input.nextLine();
					
			System.out.println(" Conteúdo em " + idiomaNome + " :");
			String conteudo = input.nextLine();
			
			System.out.println("------------------------------------------------------------");
			
			listaOrientacaoDto.add(new OrientacaoDto(null, tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao));
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
	        System.out.println("                        EDIÇÃO                              ");
	        System.out.println("============================================================");
	        System.out.println(" Selecione o que deseja alterar: \n");

	        System.out.println(" 1- Título da orientação");
	        System.out.println(" 2- Tipo de Orientação");
	        System.out.println(" 3- Conteúdo de Orientação");
	        System.out.println(" 0- Sair edição");
	        System.out.println("------------------------------------------------------------");

	        String numeroRetorno = input.nextLine();

	        switch (numeroRetorno) {
	            case "1":
	                System.out.println("------------------------------------------------------------");
	                System.out.print(" Novo título: ");
	                titulo = input.nextLine();
	                System.out.println("------------------------------------------------------------");
	                break;
	            case "2":
	                System.out.println("------------------------------------------------------------");
	                System.out.println(" Novo tipo de orientação:");
	                System.out.println(TipoOrientacao.mostrarTodasTipos(obterIdiomaOrientacao()));
	                System.out.print(" Digite o número correspondente: ");
	                String tipoStr = input.nextLine();
	                try {
	                    int tipoOrientacaoOpcao = Integer.parseInt(tipoStr);
	                    tipoOrientacao = TipoOrientacao.pegarOrientacao(tipoOrientacaoOpcao);
	                } catch (NumberFormatException e) {
	                    System.out.println(" Entrada inválida! Use um número.");
	                }
	                System.out.println("------------------------------------------------------------");
	                break;
	            case "3":
	                System.out.println("------------------------------------------------------------");
	                System.out.print(" Novo conteúdo: ");
	                conteudo = input.nextLine();
	                System.out.println("------------------------------------------------------------");
	                break;
	            case "0":
	                edicaoMenu = false;
	                break;
	            default:
	                System.out.println(" Opção inválida.");
	        }

	    } while (edicaoMenu);

	    return new OrientacaoDto(null, titulo, tipoOrientacao, conteudo, obterIdiomaOrientacao());
	}


	@Override
	public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palavraPesquisada) {
		System.out.println("============================================================");
		System.out.println("                   ORIENTAÇÕES                              ");
		System.out.println("============================================================");
		System.out.println(" S- Sair para o menu geral");
		System.out.println(" F- Filtros                                   P- Pesquisar  ");

		System.out.println(" A- Apagar pesquisa");
		System.out.println(" R- Retirar filtros  \n ");
		System.out.println("\n Resultado: " + palavraPesquisada);

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
		System.out.println("                           ERRO                             ");
		System.out.println(mensagemErro);
		System.out.println(" 1 - Continuar ");
		System.out.println("------------------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemErroLogin() {
		return "Não foi possível realizar o login";
	}

	@Override
	public String pegarMensagemErroLoginUsuario() {
		return "Usuário não existente";
	}

	@Override
	public String pegarMensagemErroLoginSenha() {
		return "Senha incorreta";
	}

	@Override
	public String pegarMensagemLoginConcluido() {
		return "Login Concluido com sucesso";
	}

	@Override
	public String pegarMensagemErroCadastroUsuarioExistente() {
		return "O usuário já está cadastrado.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaPequena() {
		return "A senha deve ter no mínimo 8 caracteres.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemMaiscula() {
		return "A senha deve conter pelo menos uma letra maiúscula.";
	}

	@Override
	public String pegarMensagemErroCadastroSenhaSemCaracterEspecial() {
		return "A senha deve conter pelo menos um caractere especial.";
	}

	@Override
	public String pegarMensagemCadastroConcluido() {
		return "Cadastro realizado com sucesso!";
	}

	@Override
	public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientacoes) {
		System.out.println("============================================================");
		System.out.println("                    ORIENTAÇÃO                              ");
		System.out.println("============================================================");
		System.out.println(" Título: " + orientacao.titulo());
		System.out.println(" Tipo: " + orientacao.tipoOrientacao());
		System.out.println("\n Conteúdo:");
		System.out.println(" " + orientacao.conteudo());
		System.out.println("\n  S - Sair         E- Editar             A-Apagar         ");
		System.out.println(" Em outros idiomas: ");
		System.out.println(idiomasOrientacoes);
		System.out.println("============================================================");

		return input.nextLine();

	}

	@Override
	public String mostrarMenuFiltro(Scanner input, GerenciadorFiltrosOrientacao gerenciador) {
		String opcaoEscolhida;

		System.out.println("============================================================");
		System.out.println("                       FILTROS                              ");
		System.out.println("============================================================");
		System.out.println(" 1 - Filtrar por Idioma");
		System.out.println(" 2 - Filtrar por Tipo de Orientação\n");
		System.out.println(" 3 - Ver os filtros");
		System.out.println(" 4 - Aplicar Filtros Selecionados");
		System.out.println("============================================================");

		opcaoEscolhida = input.nextLine();

		switch (opcaoEscolhida) {
		case "1":
			System.out.println("\nEscolha os idiomas para filtrar:");
			System.out.println("P - Português");
			System.out.println("I - Inglês");
			System.out.println("E - Espanhol");
			System.out.println("A - Alemão");
			opcaoEscolhida = input.nextLine();
			break;

		case "2":
			System.out.println("\nEscolha os tipos de orientação para filtrar:");
			System.out.println("M - Manual de Operação");
			System.out.println("S - Procedimentos de Segurança");
			System.out.println("R - Manutenção e Reparos");
			System.out.println("D - Testes e Diagnóstico");
			System.out.println("C - Manual de Conduta e Operações Setoriais");
			opcaoEscolhida = input.nextLine();
			break;
		case "3":

			System.out.println("------------------------------------------------------------");
			System.out.println(gerenciador.formatarFiltrosAtivados());
			System.out.println("------------------------------------------------------------");
			System.out.println(" 1- Voltar");
			String opcao = input.nextLine();

			switch (opcao) {
			case "2":
				System.out.println("Digite o número do filtro: ");
				return input.nextLine();
			}

		}

		return opcaoEscolhida;
	}

	@Override
	public String mostrarMenuPesquisaOrientacao(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      Pesquisa                              ");
		System.out.println("============================================================");
		System.out.println(" 1- Sair ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Digite sua pesquisa: ");
		String pesquisa = input.nextLine();
		System.out.println("============================================================");

		return pesquisa;

	}

	@Override
	public String pegarMensagemEdicaoConcluida() {
		return "Edição realizada com sucesso";
	}
	
	@Override
	public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao){
		return switch(idiomaOrientacao) {
		case PORTUGUES -> "Português";
		case INGLES -> "Inglês";
		case ESPANHOL -> "Espanhol";
		case ALEMAO -> "Alemão";
		default -> "Outro";
		};
	}

	@Override
	public String pegarMensangemAdicaoConcluida() {
		return " Adição realizada com sucesso";
	}

	@Override
	public String pegarMensangemAdicaoFalhada() {
		return " Falha ao adicionar orientação";
	}

	@Override
	public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao) {
		// TODO Auto-generated method stub
		return null;
	}

}
