package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;

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
		System.out.println(" 3- Mudar Idioma");
		System.out.println(" 4- Sair sistema");
		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public UsuarioDto mostrarMenuLogin(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        LOGIN                               ");
		System.out.println("============================================================");
		System.out.println(" Insira seus dados:\n");

		System.out.print(" Email:");
		String email = input.nextLine();

		System.out.print(" Senha:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(email, null, senha);
	}

	@Override
	public UsuarioDto mostrarMenuCadastro(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      CADASTRO                              ");
		System.out.println("============================================================");
		System.out.println(" Insira seus dados:\n");

		System.out.print(" Nome:");
		String nome = input.nextLine();

		System.out.print(" Email:");
		String email = input.nextLine();

		System.out.print(" Senha:");
		String senha = input.nextLine();

		System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, email, senha);
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
		System.out.println(" 4- Mudar de idioma");

		System.out.println("------------------------------------------------------------");

		return input.nextLine();
	}

	@Override
	public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
		List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

		System.out.println("============================================================");
		System.out.println("                       CRIAÇÃO                              ");
		System.out.println("============================================================");

		System.out.println(" V- Sair ");
		System.out.println(" Deseja criar para só seu idioma ou para todos? ");
		System.out.println(" 1- Apenas para Português ");
		System.out.println(" 2- Para todos ");
		String opcao = input.nextLine();

		if (opcao.trim().toUpperCase().equals("V")) {
			throw new SairMenuException();
		}

		System.out.println(" Tipo Orientação:");
		System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
		TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
		input.nextLine();

		switch (opcao.toUpperCase()) {
		case "1":
			listaOrientacaoDto.add(mostrarMenuAdicionarOrientacao(input, tipoOrientacao, IdiomaOrientacao.PORTUGUES));
			break;
		case "2":
			for (IdiomaOrientacao idioma : IdiomaOrientacao.listarIdiomas()) {
				listaOrientacaoDto.add(mostrarMenuAdicionarOrientacao(input, tipoOrientacao, idioma));
			}
			break;
		}

		return listaOrientacaoDto;

	}

	@Override
	public OrientacaoDto mostrarMenuAdicionarOrientacao(Scanner input, TipoOrientacao tipoOrientacao,
			IdiomaOrientacao idiomaOrientacao) {
		System.out.println("------------------------------------------------------------");
		String idiomaNome = pegarNomeIdioma(idiomaOrientacao);

		System.out.println(" Título orientação em " + idiomaNome + " :");
		String tituloOrientacao = input.nextLine();

		System.out.println(" Conteúdo em " + idiomaNome + " :");
		String conteudo = input.nextLine();

		System.out.println("------------------------------------------------------------");

		return new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao);
	}

	@Override
	public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palavraPesquisada) {
		System.out.println("============================================================");
		System.out.println("                   ORIENTAÇÕES                              ");
		System.out.println("============================================================");
		System.out.println(" V- Voltar para o menu geral");
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
		System.out.println("                       ORIENTAÇÃO                           ");
		System.out.println("============================================================");
		System.out.println(" Título: " + orientacao.titulo());
		System.out.println("\n Tipo: " + orientacao.tipoOrientacao().getNomePortugues());
		System.out.println(" Idioma: " + orientacao.idiomaOrientacao().getNomeEmPortugues());

		System.out.println("\n Conteúdo:");
		System.out.println(" " + orientacao.conteudo());
		System.out.println("\n  S - Sair         E- Editar             A-Apagar         ");
		System.out.println("------------------------------------------------------------");
		System.out.println("Visualização em outros idiomas:\n");
		System.out.println(idiomasOrientacoes);
		System.out.println("============================================================");

		return input.nextLine();

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
	public String pegarMensangemAdicaoConcluida() {
		return " Adição realizada com sucesso";
	}

	@Override
	public String pegarMensangemAdicaoFalhada() {
		return " Falha ao adicionar orientação";
	}

	@Override
	public String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    TROCAR IDIOMA                           ");
		System.out.println("============================================================");
		System.out.println(" S- Sair\n");
		System.out.println(" Idiomas disponíveis: ");
		System.out.println(idiomaFormatados);
		System.out.println("============================================================");

		return input.nextLine();
	}

	@Override
	public String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado) {
		return " Idioma Alterado para " + idiomaAlterado;
	}

	@Override
	public String pegarMensagemOrientacoesNaoEncontrada() {
		return " Nenhuma orientação encontrada";
	}

	@Override
	public String pegarIdiomaDisponivel() {
		return " Disponível: ";
	}

	@Override
	public String pegarIdiomaIndisponivel() {
		return " Indisponível: ";
	}

	@Override
	public String pegarMensagemErro() {
		return " Algo deu errado, tentar novamente";
	}

	@Override
	public String pegarMensagemEdicaoFalha() {
		return " Erro ao editar orientação";
	}

	@Override
	public String pegarMensagemRemoverComSucessoOrientacao() {
		return " Orientação removida com sucessa";
	}

	@Override
	public String pegarMensagemErroAoRemoverOrientacao() {
		return " Erro ao remover orientação";
	}

	@Override
	public OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao,
			TipoOrientacao tipoOrientacao) throws Exception {
		System.out.println("============================================================");
		System.out.println("                 ORIENTAÇÃO INDISPONIVEL                    ");
		System.out.println("============================================================");
		System.out.println("  A orientação que você pesquisou não existe nesse idioma   ");

		System.out.println("\n A- Adicionar em " + idiomaOrientacao.getNomeEmPortugues());
		System.out.println(" V- Voltar");
		System.out.println("============================================================");
		String opcao = input.nextLine();

		return switch (opcao.trim().toUpperCase()) {
		case "V" -> throw new SairMenuException();
		case "A" -> mostrarMenuAdicionarOrientacao(input, tipoOrientacao, idiomaOrientacao);
		default -> null;
		};

	}

	@Override
	public String mostrarMenuConfirmarApagarOrientacao(Scanner input) throws SairMenuException {
		System.out.println("============================================================");
		System.out.println("                      TEM CERTEZA?                          ");
		System.out.println("============================================================");
		System.out.println(" Tem certeza que deseja deletar essa orientação ?");

		System.out.println("\n A- Apagar orientação ");
		System.out.println(" C- Cancelar");
		System.out.println("============================================================");
		String opcao = input.nextLine().trim().toUpperCase();

		if (opcao.equals("C")) {
			throw new SairMenuException();
		}
		return opcao;
	}

	@Override
	public String mostrarMenuFiltro(Scanner input) {
		String opcaoEscolhida;

		System.out.println("============================================================");
		System.out.println("                       FILTROS                              ");
		System.out.println("============================================================");
		System.out.println(" V - Voltar ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" 1 - Ver os filtros ativados");
		System.out.println(" 2 - Definir filtro");
		System.out.println(" 3 - Aplicar Filtros Selecionados");
		System.out.println("============================================================");

		opcaoEscolhida = input.nextLine();

		return opcaoEscolhida;
	}


	@Override
	public String mostrarMenuApagarFiltro(Scanner input, String tipoFiltro, String filtrosDisponiveis) {
		System.out.println("============================================================");
		System.out.println("                 DELETAR FILTROS " + tipoFiltro.toUpperCase());
		System.out.println("============================================================");
		System.out.println(" V- Voltar ");
		System.out.println(" Selecione o filtro que deseja apagar");
		System.out.println(filtrosDisponiveis);
		System.out.println("============================================================");

		return input.nextLine();
	}

	@Override
	public String mostrarMenuVisualizarTiposFiltros(Scanner input, String tipoOrientacoesDisponiveis) {
		System.out.println("============================================================");
		System.out.println("                   Tipos de filtro                          ");
		System.out.println("============================================================");
		System.out.println(" V- Voltar ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Selecione o tipo de filtro desejado");
		System.out.println(tipoOrientacoesDisponiveis);
		System.out.println("============================================================");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemEntradaInvalida() {
		return " Entrada invalida";
	}

	@Override
	public String mostrarMenuVisualizarFiltros(Scanner input, String filtrosPossiveis, String tipoFiltro) {
		System.out.println("============================================================");
		System.out.println("                  FILTROS " + tipoFiltro.toUpperCase());
		System.out.println("============================================================");
		System.out.println(" V- Voltar ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Filtros " + tipoFiltro.toLowerCase());
		System.out.println(filtrosPossiveis);
		System.out.println("============================================================");
		return input.nextLine();
	}
	
	@Override
	public String mostrarMenuVisualizarFiltrosDisponiveis(Scanner input, String filtroDisponiveis, String tipoFiltro) {
		System.out.println("============================================================");
		System.out.println("                  FILTROS " + tipoFiltro.toUpperCase());
		System.out.println("============================================================");
		System.out.println(" V- Voltar ");
		System.out.println(" D- Deletar filtro");
		System.out.println("------------------------------------------------------------");
		System.out.println(" Filtros " + tipoFiltro.toLowerCase());
		System.out.println(filtroDisponiveis);
		System.out.println("============================================================");
		return input.nextLine();
	}

	@Override
	public String pegarMensagemAdicionadoNovoFiltro(Scanner input) {
		return " Filtro adicionado ";
	}

	@Override
	public String pegarMensagemFalhaAdicionarFiltro(Scanner input) {
		return " Falha ao adicionar filtro";
	}
	
	@Override
	public String mostrarMenuEditarOrientacao( Scanner input) {
		System.out.println("============================================================");
		System.out.println("                        EDIÇÃO                              ");
		System.out.println("============================================================");
		System.out.println(" Selecione o que deseja alterar: \n");

		System.out.println(" 1- Título da orientação");
		System.out.println(" 2- Tipo de Orientação");
		System.out.println(" 3- Conteúdo de Orientação");
		System.out.println(" 4- Idioma ");
		System.out.println(" V- Sair edição");
		System.out.println("------------------------------------------------------------");

		return input.nextLine();

	}

	@Override
	public String mostrarMenuMudarTituloOrientacao(Scanner input, String tituloAntigo) {
		System.out.println("============================================================");
		System.out.println("                    EDIÇÃO TÍTULO                           ");
		System.out.println("============================================================");
		System.out.println(" V - Voltar");
		System.out.println(" Título antigo: " + tituloAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.print(" Novo título: ");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarConteudoOrientacao(Scanner input, String conteudoAntigo) {
		System.out.println("============================================================");
		System.out.println("                    EDIÇÃO CONTEÚDO                           ");
		System.out.println("============================================================");
		System.out.println(" V - Voltar");
		System.out.println(" Conteúdo antigo: " + conteudoAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.print(" Novo Conteúdo: ");
		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarTipoOrientacao(Scanner input, String tipoAntigo, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    EDIÇÃO TIPO                           ");
		System.out.println("============================================================");
		System.out.println(" V - Voltar");
		System.out.println(" Tipo antigo: " + tipoAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.println(" Novo Tipo: ");
		System.out.println(idiomaFormatados);
		return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarIdiomaOrientacao(Scanner input, String idiomaAntigo, String idiomaFormatados) {
		System.out.println("============================================================");
		System.out.println("                    EDIÇÃO IDIOMA                           ");
		System.out.println("============================================================");
		System.out.println(" V - Voltar");
		System.out.println(" Idioma antigo: " + idiomaAntigo);
		System.out.println("------------------------------------------------------------");
		System.out.println(" Novo idioma: ");
		System.out.println(idiomaFormatados);
		return input.nextLine();
	}

	@Override
	public String mostrarMenuConfirmarEdicao(Scanner input) {
			System.out.println("============================================================");
			System.out.println("                      TEM CERTEZA?                          ");
			System.out.println("============================================================");
			System.out.println(" Tem certeza que deseja editar essa orientação ?");

			System.out.println("\n A- Editar orientação ");
			System.out.println(" C- Cancelar");
			System.out.println("============================================================");
			String opcao = input.nextLine().trim().toUpperCase();

			return opcao;
		}

	@Override
	public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao) {
		return switch (idiomaOrientacao) {
		case PORTUGUES -> "Português";
		case INGLES -> "Inglês";
		case ESPANHOL -> "Espanhol";
		case ALEMAO -> "Alemão";
		default -> "Outro";
		};
	}

	@Override
	public String pegarMensagemIdiomaNaoDisponivel() {
		return " Impossível de editar: idioma não disponível";
	}

	@Override
	public String mostrarMenuConfirmarMudancaTipo(Scanner input) {
		System.out.println("============================================================");
		System.out.println("                      TEM CERTEZA?                          ");
		System.out.println("============================================================");
		System.out.println(" O tipo será alterado em todas as outras orientações\n");
		
		System.out.println(" Tem certeza que deseja editar o tipo da orientação ?");
		System.out.println("\n A- Confirmar");
		System.out.println(" C- Cancelar");
		System.out.println("============================================================");
		String opcao = input.nextLine().trim().toUpperCase();

		return opcao;
	}

	@Override
	public void mostrarMenuAlteradoAtributoComSucesso() {
		System.out.println("============================================================");
		System.out.println("                 ALTERADO COM SUCESSO                       ");
		System.out.println("============================================================");
	}

	@Override
	public String pegarMensagemFiltroJaCriado() {
		return " Filtro já foi adicionado";
	}


}
