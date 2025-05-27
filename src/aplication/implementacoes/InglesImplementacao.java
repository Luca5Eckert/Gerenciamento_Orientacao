package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import infrastructure.utilitarios.FormatacaoUtil;

public class InglesImplementacao implements IdiomaImplementacao {

    @Override
    public IdiomaOrientacao obterIdiomaOrientacao() {
        return IdiomaOrientacao.INGLES;
    }

    @Override
    public String mostrarMenuInicial(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                        WELCOME                             ");
        System.out.println("============================================================");
        System.out.println(" Welcome to the Orientation Management System:              ");
        System.out.println(" 1- Login");
        System.out.println(" 2- Register");
        System.out.println(" 3- Change Language");
        System.out.println(" 4- Exit system");
        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public UsuarioDto mostrarMenuLogin(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                        LOGIN                               ");
        System.out.println("============================================================");
        System.out.println(" Enter your details:\n");

        System.out.print(" Email:");
        String email = input.nextLine();

        System.out.print(" Password:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
        return new UsuarioDto(null, email, senha);
    }

    @Override
    public UsuarioDto mostrarMenuCadastro(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                      REGISTRATION                          ");
        System.out.println("============================================================");
        System.out.println(" Enter your details:\n");

        System.out.print(" Name:");
        String nome = input.nextLine();

        System.out.print(" Email:");
        String email = input.nextLine();

        System.out.print(" Password:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
        return new UsuarioDto(nome, email, senha);
    }

    @Override
    public String mostrarMenuGeral(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                     MANAGEMENT                             ");
        System.out.println("============================================================");

        System.out.println(" 0- Create Orientation");
        System.out.println(" 1- Access Orientations");
        System.out.println(" 2- Logout");
        System.out.println(" 3- Exit system");
        System.out.println(" 4- Change language");

        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
        List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

        System.out.println("============================================================");
        System.out.println("                       CREATION                             ");
        System.out.println("============================================================");

        System.out.println(" V- Exit ");
        System.out.println(" Do you want to create for just your language or for all? ");
        System.out.println(" 1- Only for English ");
        System.out.println(" 2- For all languages ");
        String opcao = input.nextLine();

        if (opcao.trim().toUpperCase().equals("V")) {
            throw new SairMenuException();
        }

        System.out.println(" Orientation Type:");
        System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
        TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
        input.nextLine();

        switch (opcao.toUpperCase()) {
            case "1":
                listaOrientacaoDto.add(mostrarMenuAdicionarOrientacao(input, tipoOrientacao, IdiomaOrientacao.INGLES));
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

        System.out.println(" Orientation title in " + idiomaNome + " :");
        String tituloOrientacao = input.nextLine();

        System.out.println(" Content in " + idiomaNome + " :");
        String conteudo = input.nextLine();

        System.out.println("------------------------------------------------------------");

        return new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao);
    }

    @Override
    public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palavraPesquisada) {
        System.out.println("============================================================");
        System.out.println("                   ORIENTATIONS                             ");
        System.out.println("============================================================");
        System.out.println(" V- Back to main menu");
        System.out.println(" F- Filters                                   P- Search");

        System.out.println(" A- Clear search                              L- Reload");
        System.out.println(" R- Remove filters  \n ");
        System.out.println("\n Results: " + palavraPesquisada);

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
        System.out.println(" 1 - Continue ");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuErro(Scanner input, String mensagemErro) {
        System.out.println("------------------------------------------------------------");
        System.out.println("                          ERROR                             ");
        System.out.println(mensagemErro);
        System.out.println(" 1 - Continue ");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemErroLogin() {
        return "Unable to login";
    }

    @Override
    public String pegarMensagemErroLoginUsuario() {
        return "User does not exist";
    }

    @Override
    public String pegarMensagemErroLoginSenha() {
        return "Incorrect password";
    }

    @Override
    public String pegarMensagemLoginConcluido() {
        return "Login successful";
    }

    @Override
    public String pegarMensagemErroCadastroUsuarioExistente() {
        return "User already registered.";
    }

    @Override
    public String pegarMensagemErroCadastroSenhaPequena() {
        return "Password must be at least 8 characters long.";
    }

    @Override
    public String pegarMensagemErroCadastroSenhaSemMaiscula() {
        return "Password must contain at least one uppercase letter.";
    }

    @Override
    public String pegarMensagemErroCadastroSenhaSemCaracterEspecial() {
        return "Password must contain at least one special character.";
    }

    @Override
    public String pegarMensagemCadastroConcluido() {
        return "Registration completed successfully!";
    }

    @Override
    public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientacoes) {
        System.out.println("============================================================");
        System.out.println("                       ORIENTATION                          ");
        System.out.println("============================================================");
        System.out.println(" Title: " + FormatacaoUtil.enquadrarTextoNoMenu(orientacao.titulo(), 51, 9));
        System.out.println("\n Type: " + orientacao.tipoOrientacao().getNomeIngles());
        System.out.println(" Language: " + orientacao.idiomaOrientacao().getNomeEmIngles());

        System.out.println("\n Content:");
		System.out.println(" " + FormatacaoUtil.enquadrarTextoNoMenu(orientacao.conteudo(), 59, 1));
        System.out.println("\n  S - Exit         E- Edit             D-Delete         ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Available in other languages:\n");
        System.out.println(idiomasOrientacoes);
        System.out.println("============================================================");

        return input.nextLine();
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
        return "Edit completed successfully";
    }

    @Override
    public String pegarMensangemAdicaoConcluida() {
        return "Addition completed successfully";
    }

    @Override
    public String pegarMensangemAdicaoFalhada() {
        return "Failed to add orientation";
    }

    @Override
    public String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados) {
        System.out.println("============================================================");
        System.out.println("                    CHANGE LANGUAGE                         ");
        System.out.println("============================================================");
        System.out.println(" S- Exit\n");
        System.out.println(" Available languages: ");
        System.out.println(idiomaFormatados);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado) {
        return "Language changed to " + idiomaAlterado;
    }

    @Override
    public String pegarMensagemOrientacoesNaoEncontrada() {
        return "No orientations found";
    }

    @Override
    public String pegarIdiomaDisponivel() {
        return " Available: ";
    }

    @Override
    public String pegarIdiomaIndisponivel() {
        return " Unavailable: ";
    }

    @Override
    public String pegarMensagemErro() {
        return "Something went wrong, please try again";
    }

    @Override
    public String pegarMensagemEdicaoFalha() {
        return "Error editing orientation";
    }

    @Override
    public String pegarMensagemRemoverComSucessoOrientacao() {
        return "Orientation removed successfully";
    }

    @Override
    public String pegarMensagemErroAoRemoverOrientacao() {
        return "Error removing orientation";
    }

    @Override
    public OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao,
            TipoOrientacao tipoOrientacao) throws Exception {
        System.out.println("============================================================");
        System.out.println("                 UNAVAILABLE ORIENTATION                    ");
        System.out.println("============================================================");
        System.out.println(" The orientation you searched for doesn't exist in this"
        		+ " language");

        System.out.println("\n A- Add in " + idiomaOrientacao.getNomeEmIngles());
        System.out.println(" V- Back");
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
        System.out.println("                     ARE YOU SURE?                          ");
        System.out.println("============================================================");
        System.out.println(" Are you sure you want to delete this orientation?");

        System.out.println("\n A- Delete orientation ");
        System.out.println(" C- Cancel");
        System.out.println("============================================================");
        String opcao = input.nextLine().trim().toUpperCase();

        if (opcao.trim().toUpperCase().equals("C")) {
            throw new SairMenuException();
        }
        return opcao;
    }

    @Override
    public String mostrarMenuFiltro(Scanner input) {
        String opcaoEscolhida;

        System.out.println("============================================================");
        System.out.println("                        FILTERS                             ");
        System.out.println("============================================================");
        System.out.println(" V - Back ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" 1 - View active filters");
        System.out.println(" 2 - Set filter");
        System.out.println(" 3 - Apply Selected Filters");
        System.out.println("============================================================");

        opcaoEscolhida = input.nextLine();

        return opcaoEscolhida;
    }

    @Override
    public String mostrarMenuApagarFiltro(Scanner input, String tipoFiltro, String filtrosDisponiveis) {
        System.out.println("============================================================");
        System.out.println("                 DELETE FILTERS " + tipoFiltro.toUpperCase());
        System.out.println("============================================================");
        System.out.println(" V- Back ");
        System.out.println(" Select the filter you want to delete");
        System.out.println(filtrosDisponiveis);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuVisualizarTiposFiltros(Scanner input, String tipoOrientacoesDisponiveis) {
        System.out.println("============================================================");
        System.out.println("                   Filter types                             ");
        System.out.println("============================================================");
        System.out.println(" V- Back ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Select the desired filter type");
        System.out.println(tipoOrientacoesDisponiveis);
        System.out.println("============================================================");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemEntradaInvalida() {
        return "Invalid input";
    }

    @Override
    public String mostrarMenuVisualizarFiltros(Scanner input, String filtrosPossiveis, String tipoFiltro) {
        System.out.println("============================================================");
        System.out.println("                  FILTERS " + tipoFiltro.toUpperCase());
        System.out.println("============================================================");
        System.out.println(" V- Back ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" " + tipoFiltro.toLowerCase() + " filters");
        System.out.println(filtrosPossiveis);
        System.out.println("============================================================");
        return input.nextLine();
    }
    
    @Override
    public String mostrarMenuVisualizarFiltrosDisponiveis(Scanner input, String filtroDisponiveis, String tipoFiltro) {
        System.out.println("============================================================");
        System.out.println("                  FILTERS " + tipoFiltro.toUpperCase());
        System.out.println("============================================================");
        System.out.println(" V- Back ");
        System.out.println(" D- Delete filter");
        System.out.println("------------------------------------------------------------");
        System.out.println(" " + tipoFiltro.toLowerCase() + " filters");
        System.out.println(filtroDisponiveis);
        System.out.println("============================================================");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemAdicionadoNovoFiltro(Scanner input) {
        return "Filter added";
    }

    @Override
    public String pegarMensagemFalhaAdicionarFiltro(Scanner input) {
        return "Failed to add filter";
    }
    
    @Override
    public String mostrarMenuEditarOrientacao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                        EDIT                                ");
        System.out.println("============================================================");
        System.out.println(" Select what you want to change: \n");

        System.out.println(" 1- Orientation title");
        System.out.println(" 2- Orientation type");
        System.out.println(" 3- Orientation content");
        System.out.println(" 4- Language ");
        System.out.println(" V- Exit edit");
        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarTituloOrientacao(Scanner input, String tituloAntigo) {
        System.out.println("============================================================");
        System.out.println("                    TITLE EDIT                              ");
        System.out.println("============================================================");
        System.out.println(" V - Back");
        System.out.println(" Old title: " + tituloAntigo);
        System.out.println("------------------------------------------------------------");
        System.out.print(" New title: ");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarConteudoOrientacao(Scanner input, String conteudoAntigo) {
        System.out.println("============================================================");
        System.out.println("                    CONTENT EDIT                            ");
        System.out.println("============================================================");
        System.out.println(" V - Back");
        System.out.println(" Old content: " + conteudoAntigo);
        System.out.println("------------------------------------------------------------");
        System.out.print(" New content: ");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarTipoOrientacao(Scanner input, String tipoAntigo, String idiomaFormatados) {
        System.out.println("============================================================");
        System.out.println("                    TYPE EDIT                               ");
        System.out.println("============================================================");
        System.out.println(" V - Back");
        System.out.println(" Old type: " + tipoAntigo);
        System.out.println("------------------------------------------------------------");
        System.out.println(" New type: ");
        System.out.println(idiomaFormatados);
        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarIdiomaOrientacao(Scanner input, String idiomaAntigo, String idiomaFormatados) {
        System.out.println("============================================================");
        System.out.println("                    LANGUAGE EDIT                           ");
        System.out.println("============================================================");
        System.out.println(" V - Back");
        System.out.println(" Old language: " + idiomaAntigo);
        System.out.println("------------------------------------------------------------");
        System.out.println(" New language: ");
        System.out.println(idiomaFormatados);
        return input.nextLine();
    }

    @Override
    public String mostrarMenuConfirmarEdicao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                     ARE YOU SURE?                          ");
        System.out.println("============================================================");
        System.out.println(" Are you sure you want to edit this orientation?");

        System.out.println("\n A- Edit orientation ");
        System.out.println(" C- Cancel");
        System.out.println("============================================================");
        String opcao = input.nextLine().trim().toUpperCase();

        return opcao;
    }

    @Override
    public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao) {
        return switch (idiomaOrientacao) {
            case PORTUGUES -> "Portuguese";
            case INGLES -> "English";
            case ESPANHOL -> "Spanish";
            case ALEMAO -> "German";
            default -> "Other";
        };
    }

    @Override
    public String pegarMensagemIdiomaNaoDisponivel() {
        return "Cannot edit: language not available";
    }

    @Override
    public String mostrarMenuConfirmarMudancaTipo(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                     ARE YOU SURE?                          ");
        System.out.println("============================================================");
        System.out.println(" The type will be changed in all other orientations\n");
        
        System.out.println(" Are you sure you want to change the orientation type?");
        System.out.println("\n A- Confirm");
        System.out.println(" C- Cancel");
        System.out.println("============================================================");
        String opcao = input.nextLine().trim().toUpperCase();

        return opcao;
    }

    @Override
    public void mostrarMenuAlteradoAtributoComSucesso() {
        System.out.println("============================================================");
        System.out.println("                 CHANGED SUCCESSFULLY                       ");
        System.out.println("============================================================");
    }

    @Override
    public String pegarMensagemFiltroJaCriado() {
        return "Filter already added";
    }

    @Override
    public String pegarMensagemEmailInvalidoJaUsado() {
        return " The entered email is already in use";
    }

    @Override
    public String pegarMensagemEmailComSintaxeIncorreta() {
        return " Invalid email due to incorrect format \n user@domain";
    }

    @Override
    public String pegarMensagemUsuarioInvalidoLimiteDeCaracters() {
        return " Invalid username\n"
             + " Exceeded the 15-character limit";
    }

    @Override
    public String pegarMensagemUsuarioInvalidoEmBranco() {
        return " Invalid username\n" + " Blank or starts with a space";
    }

	@Override
	public String mostrarMenuOpcaoApagarOrientacao(Scanner input) throws SairMenuException {
		System.out.println("============================================================");
	    System.out.println("               SELECT DELETE OPTION                         ");
	    System.out.println("============================================================");
	    System.out.println("A- Delete only for Portuguese");
	    System.out.println("T- Delete in all languages");
	    System.out.println("C- Cancel");
	    String opcao = input.nextLine().trim().toUpperCase();

	    if (opcao.equals("C")) {
	        throw new SairMenuException();
	    }
	    return opcao;
	}

	@Override
	public String pegarMensagemVoltandoMenu() {
		return " Returning ";
	}

}