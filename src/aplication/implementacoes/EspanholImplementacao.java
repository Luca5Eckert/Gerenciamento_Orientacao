package aplication.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.NivelAcesso;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import infrastructure.utilitarios.FormatacaoUtil;

public class EspanholImplementacao implements IdiomaImplementacao {

    @Override
    public IdiomaOrientacao obterIdiomaOrientacao() {
        return IdiomaOrientacao.ESPANHOL;
    }

    @Override
    public String mostrarMenuInicial(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                        INICIO                              ");
        System.out.println("============================================================");
        System.out.println(" Bienvenido al sistema de gestión de orientaciones:        ");
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
        System.out.println("                    INICIAR SESIÓN                          ");
        System.out.println("============================================================");
        System.out.println(" Ingrese sus datos:\n");

        System.out.print(" Correo electrónico:");
        String email = input.nextLine();

        System.out.print(" Contraseña:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
        return new UsuarioDto(null, email, senha, NivelAcesso.ALTERADOR);
    }

    @Override
    public UsuarioDto mostrarMenuCadastro(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                      REGISTRO                              ");
        System.out.println("============================================================");
        System.out.println(" Ingrese sus datos:\n");

        System.out.print(" Nombre:");
        String nome = input.nextLine();

        System.out.print(" Correo electrónico:");
        String email = input.nextLine();

        System.out.print(" Contraseña:");
        String senha = input.nextLine();

        System.out.println("------------------------------------------------------------");
		return new UsuarioDto(nome, email, senha, NivelAcesso.ALTERADOR);
    }

    @Override
    public String mostrarMenuGeral(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                     GESTIÓN                                ");
        System.out.println("============================================================");

        System.out.println(" 0- Crear orientación");
        System.out.println(" 1- Acceder a orientaciones");
        System.out.println(" 2- Cerrar sesión");
        System.out.println(" 3- Salir del sistema");
        System.out.println(" 4- Cambiar idioma");
        System.out.println("5- Historial de acciones del usuario");

        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception {
        List<OrientacaoDto> listaOrientacaoDto = new ArrayList<>();

        System.out.println("============================================================");
        System.out.println("                       CREACIÓN                              ");
        System.out.println("============================================================");

        System.out.println(" V- Salir ");
        System.out.println(" ¿Desea crear para solo su idioma o para todos? ");
        System.out.println(" 1- Solo para Español ");
        System.out.println(" 2- Para todos los idiomas ");
        String opcao = input.nextLine();

        if (opcao.trim().toUpperCase().equals("V")) {
            throw new SairMenuException();
        }

        System.out.println(" Tipo de orientación:");
        System.out.println(TipoOrientacao.mostrarTodasTipos(this.obterIdiomaOrientacao()));
        TipoOrientacao tipoOrientacao = TipoOrientacao.pegarOrientacao(input.nextInt());
        input.nextLine();

        switch (opcao.toUpperCase()) {
            case "1":
                listaOrientacaoDto.add(mostrarMenuAdicionarOrientacao(input, tipoOrientacao, IdiomaOrientacao.ESPANHOL));
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
        String idiomaNombre = pegarNomeIdioma(idiomaOrientacao);

        System.out.println(" Título de la orientación en " + idiomaNombre + " :");
        String tituloOrientacion = input.nextLine();

        System.out.println(" Contenido en " + idiomaNombre + " :");
        String contenido = input.nextLine();

        System.out.println("------------------------------------------------------------");

        return new OrientacaoDto(tituloOrientacion, tipoOrientacao, contenido, idiomaOrientacao);
    }

    @Override
    public String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palabraPesquisada) {
        System.out.println("============================================================");
        System.out.println("                   ORIENTACIONES                            ");
        System.out.println("============================================================");
        System.out.println(" V- Volver al menú principal");
        System.out.println(" F- Filtros                                   P- Buscar     ");

        System.out.println(" A- Borrar búsqueda                           L- Recargar");
        System.out.println(" R- Quitar filtros  \n ");
        System.out.println("\n Resultados: " + palabraPesquisada);

        System.out.println(listaFormatada);

        System.out.println("============================================================");
        String orientacionSeleccionada = input.nextLine();

        return orientacionSeleccionada;
    }

    @Override
    public String mostrarMenuOpcoesOrientacao(Scanner input) {
        return null;
    }

    @Override
    public String mostrarMenuAcerto(Scanner input, String mensagemAcerto) {
        System.out.println("------------------------------------------------------------");
        System.out.println(mensagemAcerto);
        System.out.println(" 1- Continuar ");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuErro(Scanner input, String mensagemErro) {
        System.out.println("------------------------------------------------------------");
        System.out.println("                          ERROR                             ");
        System.out.println(mensagemErro);
        System.out.println(" 1- Continuar ");
        System.out.println("------------------------------------------------------------");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemErroLogin() {
        return "No se pudo iniciar sesión";
    }

    @Override
    public String pegarMensagemErroLoginUsuario() {
        return "Usuario no existe";
    }

    @Override
    public String pegarMensagemErroLoginSenha() {
        return "Contraseña incorrecta";
    }

    @Override
    public String pegarMensagemLoginConcluido() {
        return "Inicio de sesión exitoso";
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

    @Override
    public String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientaciones) {
        System.out.println("============================================================");
        System.out.println("                       ORIENTACIÓN                          ");
        System.out.println("============================================================");
        System.out.println(" Título: " + FormatacaoUtil.enquadrarTextoNoMenu(orientacao.titulo(), 51, 9));
        System.out.println("\n Tipo: " + orientacao.tipoOrientacao().getNomeEspanhol());
        System.out.println(" Idioma: " + orientacao.idiomaOrientacao().getNomeEmEspanhol());

        System.out.println("\n Contenido:");
		System.out.println(" " + FormatacaoUtil.enquadrarTextoNoMenu(orientacao.titulo(), 59, 1));
        System.out.println("\n  S- Salir         E- Editar             B-Borrar         ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Disponible en otros idiomas:\n");
        System.out.println(idiomasOrientaciones);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuPesquisaOrientacao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                       BÚSQUEDA                             ");
        System.out.println("============================================================");
        System.out.println(" 1- Salir ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Ingrese su búsqueda: ");
        String busqueda = input.nextLine();
        System.out.println("============================================================");

        return busqueda;
    }

    @Override
    public String pegarMensagemEdicaoConcluida() {
        return "Edición completada con éxito";
    }

    @Override
    public String pegarMensangemAdicaoConcluida() {
        return "Adición completada con éxito";
    }

    @Override
    public String pegarMensangemAdicaoFalhada() {
        return "Error al agregar orientación";
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
        return "Idioma cambiado a " + idiomaAlterado;
    }

    @Override
    public String pegarMensagemOrientacoesNaoEncontrada() {
        return "No se encontraron orientaciones";
    }

    @Override
    public String pegarIdiomaDisponivel() {
        return " Disponible: ";
    }

    @Override
    public String pegarIdiomaIndisponivel() {
        return " No disponible: ";
    }

    @Override
    public String pegarMensagemErro() {
        return "Algo salió mal, intente nuevamente";
    }

    @Override
    public String pegarMensagemEdicaoFalha() {
        return "Error al editar orientación";
    }

    @Override
    public String pegarMensagemRemoverComSucessoOrientacao() {
        return "Orientación eliminada con éxito";
    }

    @Override
    public String pegarMensagemErroAoRemoverOrientacao() {
        return "Error al eliminar orientación";
    }

    @Override
    public OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao,
            TipoOrientacao tipoOrientacao) throws Exception {
        System.out.println("============================================================");
        System.out.println("                 ORIENTACIÓN NO DISPONIBLE                  ");
        System.out.println("============================================================");
        System.out.println(" La orientación que buscaste no existe en este idioma       ");

        System.out.println("\n A- Agregar en " + idiomaOrientacao.getNomeEmEspanhol());
        System.out.println(" V- Volver");
        System.out.println("============================================================");
        String opcion = input.nextLine();

        return switch (opcion.trim().toUpperCase()) {
            case "V" -> throw new SairMenuException();
            case "A" -> mostrarMenuAdicionarOrientacao(input, tipoOrientacao, idiomaOrientacao);
            default -> null;
        };
    }

    @Override
    public String mostrarMenuConfirmarApagarOrientacao(Scanner input) throws SairMenuException {
        System.out.println("============================================================");
        System.out.println("                     ¿ESTÁ SEGURO?                          ");
        System.out.println("============================================================");
        System.out.println(" ¿Está seguro que desea eliminar esta orientación?");

        System.out.println("\n A- Borrar orientación ");
        System.out.println(" C- Cancelar");
        System.out.println("============================================================");
        String opcao = input.nextLine().trim().toUpperCase();

        if (opcao.trim().toUpperCase().equals("C")) {
            throw new SairMenuException();
        }
        return opcao;
    }

    @Override
    public String mostrarMenuFiltro(Scanner input) {
        String opcionElegida;

        System.out.println("============================================================");
        System.out.println("                       FILTROS                             ");
        System.out.println("============================================================");
        System.out.println(" V- Volver ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" 1- Ver filtros activos");
        System.out.println(" 2- Definir filtro");
        System.out.println(" 3- Aplicar filtros seleccionados");
        System.out.println("============================================================");

        opcionElegida = input.nextLine();

        return opcionElegida;
    }

    @Override
    public String mostrarMenuApagarFiltro(Scanner input, String tipoFiltro, String filtrosDisponibles) {
        System.out.println("============================================================");
        System.out.println("                 ELIMINAR FILTROS " + tipoFiltro.toUpperCase());
        System.out.println("============================================================");
        System.out.println(" V- Volver ");
        System.out.println(" Seleccione el filtro que desea eliminar");
        System.out.println(filtrosDisponibles);
        System.out.println("============================================================");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuVisualizarTiposFiltros(Scanner input, String tipoOrientacionesDisponibles) {
        System.out.println("============================================================");
        System.out.println("                   TIPOS DE FILTRO                          ");
        System.out.println("============================================================");
        System.out.println(" V- Volver ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Seleccione el tipo de filtro deseado");
        System.out.println(tipoOrientacionesDisponibles);
        System.out.println("============================================================");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemEntradaInvalida() {
        return "Entrada inválida";
    }

    @Override
    public String mostrarMenuVisualizarFiltros(Scanner input, String filtrosPosibles, String tipoFiltro) {
        System.out.println("============================================================");
        System.out.println("                  FILTROS " + tipoFiltro.toUpperCase());
        System.out.println("============================================================");
        System.out.println(" V- Volver ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Filtros " + tipoFiltro.toLowerCase());
        System.out.println(filtrosPosibles);
        System.out.println("============================================================");
        return input.nextLine();
    }
    
    @Override
    public String mostrarMenuVisualizarFiltrosDisponiveis(Scanner input, String filtroDisponibles, String tipoFiltro) {
        System.out.println("============================================================");
        System.out.println("                  FILTROS " + tipoFiltro.toUpperCase());
        System.out.println("============================================================");
        System.out.println(" V- Volver ");
        System.out.println(" D- Eliminar filtro");
        System.out.println("------------------------------------------------------------");
        System.out.println(" Filtros " + tipoFiltro.toLowerCase());
        System.out.println(filtroDisponibles);
        System.out.println("============================================================");
        return input.nextLine();
    }

    @Override
    public String pegarMensagemAdicionadoNovoFiltro(Scanner input) {
        return "Filtro agregado";
    }

    @Override
    public String pegarMensagemFalhaAdicionarFiltro(Scanner input) {
        return "Error al agregar filtro";
    }
    
    @Override
    public String mostrarMenuEditarOrientacao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                        EDITAR                              ");
        System.out.println("============================================================");
        System.out.println(" Seleccione lo que desea cambiar: \n");

        System.out.println(" 1- Título de la orientación");
        System.out.println(" 2- Tipo de orientación");
        System.out.println(" 3- Contenido de la orientación");
        System.out.println(" 4- Idioma ");
        System.out.println(" V- Salir de edición");
        System.out.println("------------------------------------------------------------");

        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarTituloOrientacao(Scanner input, String tituloAntiguo) {
        System.out.println("============================================================");
        System.out.println("                    EDITAR TÍTULO                           ");
        System.out.println("============================================================");
        System.out.println(" V- Volver");
        System.out.println(" Título antiguo: " + tituloAntiguo);
        System.out.println("------------------------------------------------------------");
        System.out.print(" Nuevo título: ");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarConteudoOrientacao(Scanner input, String contenidoAntiguo) {
        System.out.println("============================================================");
        System.out.println("                    EDITAR CONTENIDO                         ");
        System.out.println("============================================================");
        System.out.println(" V- Volver");
        System.out.println(" Contenido antiguo: " + contenidoAntiguo);
        System.out.println("------------------------------------------------------------");
        System.out.print(" Nuevo contenido: ");
        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarTipoOrientacao(Scanner input, String tipoAntiguo, String idiomaFormatados) {
        System.out.println("============================================================");
        System.out.println("                    EDITAR TIPO                             ");
        System.out.println("============================================================");
        System.out.println(" V- Volver");
        System.out.println(" Tipo antiguo: " + tipoAntiguo);
        System.out.println("------------------------------------------------------------");
        System.out.println(" Nuevo tipo: ");
        System.out.println(idiomaFormatados);
        return input.nextLine();
    }

    @Override
    public String mostrarMenuMudarIdiomaOrientacao(Scanner input, String idiomaAntiguo, String idiomaFormatados) {
        System.out.println("============================================================");
        System.out.println("                    EDITAR IDIOMA                           ");
        System.out.println("============================================================");
        System.out.println(" V- Volver");
        System.out.println(" Idioma antiguo: " + idiomaAntiguo);
        System.out.println("------------------------------------------------------------");
        System.out.println(" Nuevo idioma: ");
        System.out.println(idiomaFormatados);
        return input.nextLine();
    }

    @Override
    public String mostrarMenuConfirmarEdicao(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                     ¿ESTÁ SEGURO?                          ");
        System.out.println("============================================================");
        System.out.println(" ¿Está seguro que desea editar esta orientación?");

        System.out.println("\n A- Editar orientación ");
        System.out.println(" C- Cancelar");
        System.out.println("============================================================");
        String opcion = input.nextLine().trim().toUpperCase();

        return opcion;
    }

    @Override
    public String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacion) {
        return switch (idiomaOrientacion) {
            case PORTUGUES -> "Portugués";
            case INGLES -> "Inglés";
            case ESPANHOL -> "Español";
            case ALEMAO -> "Alemán";
            default -> "Otro";
        };
    }

    @Override
    public String pegarMensagemIdiomaNaoDisponivel() {
        return "No se puede editar: idioma no disponible";
    }

    @Override
    public String mostrarMenuConfirmarMudancaTipo(Scanner input) {
        System.out.println("============================================================");
        System.out.println("                     ¿ESTÁ SEGURO?                          ");
        System.out.println("============================================================");
        System.out.println(" El tipo se cambiará en todas las demás orientaciones\n");
        
        System.out.println(" ¿Está seguro que desea cambiar el tipo de orientación?");
        System.out.println("\n A- Confirmar");
        System.out.println(" C- Cancelar");
        System.out.println("============================================================");
        String opcion = input.nextLine().trim().toUpperCase();

        return opcion;
    }


    @Override
    public String pegarMensagemFiltroJaCriado() {
        return "Filtro ya agregado";
    }

	@Override
	public void mostrarMenuAlteradoAtributoComSucesso() {
        System.out.println("============================================================");
        System.out.println("                 CAMBIO EXITOSO                             ");
        System.out.println("============================================================");
    }

	@Override
	public String pegarMensagemEmailInvalidoJaUsado() {
	    return " El correo electrónico ingresado ya está en uso";
	}

	@Override
	public String pegarMensagemEmailComSintaxeIncorreta() {
	    return " Correo electrónico no válido por formato incorrecto \n usuario@dominio";
	}

	@Override
	public String pegarMensagemUsuarioInvalidoLimiteDeCaracters() {
	    return " Nombre de usuario no válido\n"
	         + " Superó el límite de 15 caracteres";
	}

	@Override
	public String pegarMensagemUsuarioInvalidoEmBranco() {
	    return " Nombre de usuario no válido\n" + " En blanco o comienza con un espacio";
	}
	
	@Override
	public String mostrarMenuOpcaoApagarOrientacao(Scanner input) throws SairMenuException {
	    System.out.println("============================================================");
	    System.out.println("               SELECCIONAR OPCIÓN DE BORRADO                ");
	    System.out.println("============================================================");
	    System.out.println("A- Borrar solo en portugués");
	    System.out.println("T- Borrar en todos los idiomas");
	    System.out.println("C- Cancelar");
	    String opcao = input.nextLine().trim().toUpperCase();

	    if (opcao.equals("C")) {
	        throw new SairMenuException();
	    }
	    return opcao;
	}

	@Override
	public String pegarMensagemVoltandoMenu() {
		return " Volviendo ";
	}

	@Override
	public String pegarMensagemNivelDeAcessoInsuficiente() {
		return " Nivel de acceso insuficiente";
	}

	@Override
	public String pegarMensagemTituloNaoDisponivel() {
		return " No se puede crear: el título ya está en uso";
	}
	
	@Override
	public String pegarMensagemSemComandoDisponivel() {
	    return " Aún no se ha ejecutado ningún comando para el usuario.";
	}

	@Override
	public String pegarMensagemCanceladoAcao() {
	    return "============================================================\n"
	         + "                     Acción cancelada                      \n"
	         + "============================================================";
	}

	@Override
	public String pegarMensagemHistoricoApagado() {
	    return "============================================================\n"
	         + "                    Historial borrado                       \n"
	         + "============================================================";
	}

	@Override
	public String pegarMensagemErroAoMexerNoHistorico() {
	    return "============================================================\n"
	         + "              Error al modificar el historial              \n"
	         + "============================================================\n";
	}

	@Override
	public String pegarMensagemErroComando() {
	    return "============================================================\n"
	         + "              No se puede deshacer el comando              \n"
	         + "              La orientación ya fue modificada             \n"
	         + "============================================================";
	}

	@Override
	public String mostrarMenuHistorico(Scanner input, String historicoComandosUsuario) {
	    System.out.println("============================================================");
	    System.out.println("                  HISTORIAL DE COMANDOS                     ");
	    System.out.println("============================================================");
	    System.out.println(" S - Salir del Menú                 A - Borrar Historial \n");
	    System.out.println(" -----------------------------------------------------------");
	    System.out.println(" V - Comando Anterior   F - Comando Siguiente");
	    System.out.println(" Comandos:");
	    System.out.println(historicoComandosUsuario);
	    System.out.println("------------------------------------------------------------");
	    return input.nextLine();
	}

	@Override
	public String confirmarApagarHistorico(Scanner input) {
	    System.out.println("============================================================");
	    System.out.println("                    ¿ESTÁS SEGURO?                          ");
	    System.out.println("============================================================");
	    System.out.println(" Al borrar el historial, no se podrá acceder nuevamente.");
	    System.out.println(" * Todos los comandos permanecerán en el sistema.");
	    System.out.println(" M - Confirmar borrado");
	    System.out.println(" C - Cancelar");
	    System.out.println(" -----------------------------------------------------------");
	    return input.nextLine();
	}



}