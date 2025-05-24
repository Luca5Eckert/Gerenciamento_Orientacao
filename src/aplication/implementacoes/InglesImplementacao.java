package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;

public class InglesImplementacao implements IdiomaImplementacao {

    @Override
    public IdiomaOrientacao obterIdiomaOrientacao() {
        return IdiomaOrientacao.INGLES;
    }

    @Override
    public String mostrarMenuInicial(Scanner input) {
    	System.out.println("============================================================");
    	System.out.println("                          START                              ");
    	System.out.println("============================================================");
    	System.out.println(" Welcome to the Orientation Management System:              ");
    	System.out.println(" 1- Login");
    	System.out.println(" 2- Register");
    	System.out.println(" 3- Change Language");
    	System.out.println(" 4- Exit System");
    	System.out.println("------------------------------------------------------------");


        return input.nextLine();
    }

    @Override
    public UsuarioDto mostrarMenuLogin(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                          LOGIN                             ");
        System.out.println("============================================================");
        System.out.println(" Enter your credentials:\n");

        System.out.print(" Email:");
        String email = input.nextLine();

        System.out.print(" Password:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
        return new UsuarioDto(email, null, senha);
    }

    @Override
    public UsuarioDto mostrarMenuCadastro(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                         REGISTER                           ");
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
    	System.out.println("                        MANAGER                              ");
    	System.out.println("============================================================");

    	System.out.println(" 0- Create Orientation");
    	System.out.println(" 1- Access Orientations");
    	System.out.println(" 2- Log Out");
    	System.out.println(" 3- Exit System");
    	System.out.println(" 4- Change Language");

    	System.out.println("------------------------------------------------------------");


        return input.nextLine();
    }

    @Override
    public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
        List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

        System.out.println("============================================================");
        System.out.println("                       CREATION                             ");
        System.out.println("============================================================");

        System.out.println(" S- Exit ");
        System.out.println(" Do you want to create only for your language or for all? ");
        System.out.println(" 1- Only for English ");
        System.out.println(" 2- For all ");
        String opcao = input.nextLine();

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
            case "S":
                throw new Exception();
        }

        return listaOrientacaoDto;
    }

    @Override
    public OrientacaoDto mostrarMenuAdicionarOrientacao(Scanner input, TipoOrientacao tipoOrientacao, IdiomaOrientacao idiomaOrientacao) {
    	String idiomaNome = pegarNomeIdioma(idiomaOrientacao);

    	System.out.println("------------------------------------------------------------");

    	System.out.println(" Orientation title in " + idiomaNome + " :");
        String tituloOrientacao = input.nextLine();

        System.out.println(" Content in " + idiomaNome + " :");
        String conteudo = input.nextLine();

        System.out.println("------------------------------------------------------------");

        return new OrientacaoDto(tituloOrientacao, tipoOrientacao, conteudo, idiomaOrientacao);
    }


    @Override
    public String mostrarMenuOrientacaoDisponiveis(Scanner input, String formattedList, String searchTerm) {
        System.out.println("============================================================");
        System.out.println("                       GUIDANCES                            ");
        System.out.println("============================================================");
        System.out.println(" V - Back to main menu");
        System.out.println(" F - Filters                                P - Search");
        System.out.println(" A - Clear search");
        System.out.println(" R - Remove filters\n ");
        System.out.println("\n Result: " + searchTerm);
        System.out.println(formattedList);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuOpcoesOrientacao(Scanner input) {
        return null;
    }

    @Override
    public String mostrarMenuAcerto(Scanner input, String successMessage) {
        System.out.println("------------------------------------------------------------");
        System.out.println(successMessage);
        System.out.println(" 1 - Continue");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuErro(Scanner input, String errorMessage) {
        System.out.println("------------------------------------------------------------");
        System.out.println("                            ERROR                           ");
        System.out.println(errorMessage);
        System.out.println(" 1 - Continue");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemErroLogin() {
        return "Login failed.";
    }

    @Override
    public String pegarMensagemErroLoginUsuario() {
        return "User does not exist.";
    }

    @Override
    public String pegarMensagemErroLoginSenha() {
        return "Incorrect password.";
    }

    @Override
    public String pegarMensagemLoginConcluido() {
        return "Login successful.";
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
        return "Registration successful!";
    }

    @Override
    public String mostrarOrientacao(Scanner input, OrientacaoDto dto, String otherLanguages) {
        System.out.println("============================================================");
        System.out.println("                       GUIDANCE                             ");
        System.out.println("============================================================");
        System.out.println(" Title: " + dto.titulo());
		System.out.println("\n Type: " + dto.tipoOrientacao().getNomeIngles());
		System.out.println(" Language: " + dto.idiomaOrientacao().getNomeEmIngles());
        System.out.println("\n Content:");
        System.out.println(" " + dto.conteudo());
        System.out.println("\n  S - Exit      E - Edit      A - Delete");
        System.out.println("------------------------------------------------------------");
        System.out.println(" In other languages: ");
        System.out.println(otherLanguages);
        System.out.println("============================================================");

        return input.nextLine();
    }


    @Override
    public String mostrarMenuPesquisaOrientacao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                         SEARCH                             ");
        System.out.println("============================================================");
        System.out.println(" 1- Exit ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Enter your search: ");
        String search = input.nextLine();
        System.out.println("============================================================");

        return search;
    }

    @Override
    public String pegarMensagemEdicaoConcluida() {
        return "Edit successful.";
    }

    @Override
    public String pegarNomeIdioma(IdiomaOrientacao idioma) {
        return switch (idioma) {
            case PORTUGUES -> "Portuguese";
            case INGLES -> "English";
            case ESPANHOL -> "Spanish";
            case ALEMAO -> "German";
            default -> "Other";
        };
    }

    @Override
    public String pegarMensangemAdicaoConcluida() {
        return "Addition successful.";
    }

    @Override
    public String pegarMensangemAdicaoFalhada() {
        return "Failed to add guidance.";
    }

    @Override
    public String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados) {
        System.out.println("============================================================");
        System.out.println("                      CHANGE LANGUAGE                       ");
        System.out.println("============================================================");
        System.out.println(" S- Exit\n");
        System.out.println(" Available languages: ");
        System.out.println(idiomaFormatados);
        System.out.println("============================================================");
        
        return input.nextLine();
    }

    @Override
    public String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado) {
        return " Language changed to " + idiomaAlterado;
    }

    @Override
    public String pegarMensagemOrientacoesNaoEncontrada() {
        return " No guidance found";
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
        return " Something went wrong, please try again";
    }

    @Override
    public String pegarMensagemEdicaoFalha() {
        return " Failed to edit guidance";
    }

    @Override
    public String pegarMensagemRemoverComSucessoOrientacao() {
        return " Guidance successfully removed";
    }

    @Override
    public String pegarMensagemErroAoRemoverOrientacao() {
        return " Failed to remove guidance";
    }
    
    @Override
    public OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao, TipoOrientacao tipoOrientacao) throws Exception {
        System.out.println("============================================================");
        System.out.println("                    ORIENTATION UNAVAILABLE                 ");
        System.out.println("============================================================");
        System.out.println("  The orientation you searched for does not exist in this"
        		+ "\n language");

        System.out.println("\n A- Add in " + idiomaOrientacao.getNomeEmIngles());
        System.out.println(" V- Go back");
        System.out.println("============================================================");
        String opcao = input.nextLine();

        return switch(opcao.trim().toUpperCase()) {
			case "V" -> throw new SairMenuException();
            case "A" -> mostrarMenuAdicionarOrientacao(input, tipoOrientacao, idiomaOrientacao);
            default -> null;
        };
    }

	@Override
	public String mostrarMenuConfirmarApagarOrientacao(Scanner input) throws SairMenuException {
		System.out.println("============================================================");
	    System.out.println("                      ARE YOU SURE?                         ");
	    System.out.println("============================================================");
	    System.out.println(" Are you sure you want to delete this guidance?");
	    
	    System.out.println("\n A - Delete guidance");
	    System.out.println(" C - Cancel");
	    System.out.println("============================================================");
	    String opcao = input.nextLine().trim().toUpperCase();
	    
	    if(opcao.equals("C")) {
	        throw new SairMenuException();
	    }
	    return opcao;
	}


	@Override
	public String mostrarMenuFiltro(Scanner input) {
	    String opcaoEscolhida;

	    System.out.println("============================================================");
	    System.out.println("                       FILTERS                              ");
	    System.out.println("============================================================");
	    System.out.println(" V - Back ");
	    System.out.println("------------------------------------------------------------");
	    System.out.println(" 1 - View active filters");
	    System.out.println(" 2 - Set filter");
	    System.out.println(" 3 - Apply selected filters");
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
	    return " Invalid input";
	}

	@Override
	public String mostrarMenuVisualizarFiltros(Scanner input, String filtrosPossiveis, String tipoFiltro) {
	    System.out.println("============================================================");
	    System.out.println("                  FILTERS " + tipoFiltro.toUpperCase());
	    System.out.println("============================================================");
	    System.out.println(" V- Back ");
	    System.out.println("------------------------------------------------------------");
	    System.out.println(" Filters " + tipoFiltro.toLowerCase());
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
	    System.out.println(" Filters " + tipoFiltro.toLowerCase());
	    System.out.println(filtroDisponiveis);
	    System.out.println("============================================================");
	    return input.nextLine();
	}

	@Override
	public String pegarMensagemAdicionadoNovoFiltro(Scanner input) {
	    return " Filter added ";
	}

	@Override
	public String pegarMensagemFalhaAdicionarFiltro(Scanner input) {
	    return " Failed to add filter";
	}

	@Override
	public String mostrarMenuEditarOrientacao(Scanner input) {
	    System.out.println("============================================================");
	    System.out.println("                        EDITING                             ");
	    System.out.println("============================================================");
	    System.out.println(" Select what you want to change: \n");

	    System.out.println(" 1- Orientation title");
	    System.out.println(" 2- Orientation type");
	    System.out.println(" 3- Orientation content");
	    System.out.println(" 4- Language ");
	    System.out.println(" V- Exit editing");
	    System.out.println("------------------------------------------------------------");

	    return input.nextLine();
	}

	@Override
	public String mostrarMenuMudarTituloOrientacao(Scanner input, String tituloAntigo) {
	    System.out.println("============================================================");
	    System.out.println("                    EDIT TITLE                             ");
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
	    System.out.println("                    EDIT CONTENT                            ");
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
	    System.out.println("                    EDIT TYPE                              ");
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
	    System.out.println("                    EDIT LANGUAGE                          ");
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
	    System.out.println("                      ARE YOU SURE?                          ");
	    System.out.println("============================================================");
	    System.out.println(" Are you sure you want to edit this orientation?");

	    System.out.println("\n A- Edit orientation ");
	    System.out.println(" C- Cancel");
	    System.out.println("============================================================");
	    String opcao = input.nextLine().trim().toUpperCase();

	    return opcao;
	}

	@Override
	public String pegarMensagemIdiomaNaoDisponivel() {
	    return " Cannot edit: language not available";
	}

	@Override
	public String mostrarMenuConfirmarMudancaTipo(Scanner input) {
	    System.out.println("============================================================");
	    System.out.println("                      ARE YOU SURE?                          ");
	    System.out.println("============================================================");
	    System.out.println(" The type will be changed in all other orientations\n");
	    
	    System.out.println(" Are you sure you want to edit the orientation type?");
	    System.out.println("\n A- Confirm");
	    System.out.println(" C- Cancel");
	    System.out.println("============================================================");
	    String opcao = input.nextLine().trim().toUpperCase();

	    return opcao;
	}

	@Override
	public void mostrarMenuAlteradoAtributoComSucesso() {
	    System.out.println("============================================================");
	    System.out.println("                 SUCCESSFULLY CHANGED                       ");
	    System.out.println("============================================================");
	}




}
