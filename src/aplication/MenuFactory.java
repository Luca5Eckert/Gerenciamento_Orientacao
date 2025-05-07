package aplication;

import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.*;
import dtos.OrientacaoDto;
import repositorio.UsuarioRepositorio;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.UsuarioService;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.formatacao.FormatacaoListaOrientacao;

public class MenuFactory {

    public static UsuarioService criarUsuarioService() {
        IdiomaImplementacao idioma = pegarIdioma();
        return new UsuarioService(new UsuarioRepositorio(), idioma);
    }

    private static IdiomaImplementacao pegarIdioma() {
        return SessaoUsuario.pegarIdioma();
    }

    public static OrientacaoService criarOrientacaoService() {
        return new OrientacaoService();
    }

    public static GerenciadorFiltrosOrientacao criarGerenciadorFiltro() {
        IdiomaImplementacao idioma = pegarIdioma();
        return new GerenciadorFiltrosOrientacao(idioma.obterIdiomaOrientacao());
    }

    public static Menu criarMenu(TipoMenu tipoMenu) {
        return switch (tipoMenu) {
            case INICIO -> new MenuInicial();
            case FIM -> new MenuFinal();
            case GERAL -> new MenuGeral();
            default -> new MenuInicial();
        };
    }

    public static Menu criarMenuComIdioma(TipoMenu tipoMenu) {
        IdiomaImplementacao idioma = pegarIdioma();
        return switch (tipoMenu) {
            case CADASTRO -> new MenuCadastro(criarUsuarioService());
            case LOGIN -> new MenuLogin(criarUsuarioService());
            case ADICAO_ORIENTACAO -> new MenuAdicaoOrientacao(criarOrientacaoService());
            case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), criarGerenciadorFiltro(), new FormatacaoListaOrientacao());
            case FILTRO -> new MenuFiltro(criarGerenciadorFiltro());
            default -> new MenuInicial();
        };
    }

    public static Menu criarMenuComFiltros(TipoMenu tipoMenu, GerenciadorFiltrosOrientacao gerenciadorFiltros) {
        return switch (tipoMenu) {
            case FILTRO -> new MenuFiltro(gerenciadorFiltros);
            case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), gerenciadorFiltros, new FormatacaoListaOrientacao());
            case PESQUISA_ORIENTACAO -> new MenuPesquisaOrientacao(gerenciadorFiltros);
            default -> new MenuGeral();
        };
    }

    public static Menu criarMenuResultado(TipoMenu tipoMenu, Menu proximo, String mensagem) {
        return switch (tipoMenu) {
            case CERTO -> new MenuCerto(proximo, mensagem);
            case FALHA -> new MenuFalha(proximo, mensagem);
            default -> new MenuInicial();
        };
    }

    public static Menu criarMenuPesquisa(TipoMenu tipoMenu, OrientacaoDto orientacaoDto, Menu menu) {
        return switch (tipoMenu) {
            case MOSTRAR_ORIENTACAO -> new MenuVisualizarOrientacao(orientacaoDto, menu, new FormatacaoListaOrientacao());
            default -> new MenuInicial();
        };
    }
}
