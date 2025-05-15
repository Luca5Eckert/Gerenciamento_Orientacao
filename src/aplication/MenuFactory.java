package aplication;

import java.util.List;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.*;
import dtos.OrientacaoDto;
import repositorio.UsuarioRepositorio;
import service.OrientacaoService;
import service.UsuarioService;
import service.filtros.FiltroFactory;
import service.filtros.FiltroOrientacaoIdioma;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.filtros.TipoFiltro;
import service.formatacao.FormatacaoListaOrientacao;

public class MenuFactory {

    public static UsuarioService criarUsuarioService(IdiomaImplementacao idioma) {
        return new UsuarioService(new UsuarioRepositorio(), idioma);
    }

    public static OrientacaoService criarOrientacaoService() {
        return new OrientacaoService();
    }

    public static GerenciadorFiltrosOrientacao criarGerenciadorFiltro(IdiomaImplementacao idioma) {
        var gerenciador = new GerenciadorFiltrosOrientacao();
        var filtroOrientacaoIdioma = FiltroFactory.criarFiltroOrientacaoIdioma(); 
        
        filtroOrientacaoIdioma.adicionarFiltro(idioma.obterIdiomaOrientacao());

        gerenciador.adicionarTipoFiltro(TipoFiltro.IDIOMA ,filtroOrientacaoIdioma);
        
        return gerenciador;
    }

    public static Menu criarMenu(TipoMenu tipoMenu, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case INICIO -> new MenuInicial(idioma);
            case FIM -> new MenuFinal();
            case GERAL -> new MenuGeral(idioma);
            default -> new MenuInicial(idioma);
        };
    }

    public static Menu criarMenuComIdioma(TipoMenu tipoMenu, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case CADASTRO -> new MenuCadastro(idioma, criarUsuarioService(idioma));
            case LOGIN -> new MenuLogin(idioma, criarUsuarioService(idioma));
            case ADICAO_ORIENTACAO -> new MenuAdicaoOrientacao(idioma, criarOrientacaoService());
            case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), criarGerenciadorFiltro(idioma),
                    new FormatacaoListaOrientacao(), idioma);
            case FILTRO -> new MenuFiltro(idioma, criarGerenciadorFiltro(idioma));
            default -> new MenuInicial(idioma);
        };
    }

    public static Menu criarMenuComFiltros(TipoMenu tipoMenu, GerenciadorFiltrosOrientacao gerenciadorFiltros, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case FILTRO -> new MenuFiltro(idioma, gerenciadorFiltros);
            case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), gerenciadorFiltros,
                    new FormatacaoListaOrientacao(), idioma);
            case PESQUISA_ORIENTACAO -> new MenuPesquisaOrientacao(idioma, gerenciadorFiltros);
            default -> new MenuGeral(idioma);
        };
    }

    public static Menu criarMenuResultado(TipoMenu tipoMenu, Menu proximo, String mensagem, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case CERTO -> new MenuCerto(idioma, proximo, mensagem);
            case FALHA -> new MenuFalha(idioma, proximo, mensagem);
            default -> new MenuInicial(idioma);
        };
    }

    public static Menu criarMenuPesquisa(TipoMenu tipoMenu, OrientacaoDto orientacaoDto, Menu menu, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case MOSTRAR_ORIENTACAO -> new MenuVisualizarOrientacao(idioma, orientacaoDto, menu,
                    criarOrientacaoService(), new FormatacaoListaOrientacao());
            case EDICAO_ORIENTACAO -> new MenuEditarOrientacao(menu, orientacaoDto, idioma, criarOrientacaoService());
            default -> new MenuInicial(idioma);
        };
    }

    public static Menu criarMenuAlterarSistema(TipoMenu tipoMenu, Menu menuAnterior, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case ALTERAR_IDIOMA -> new MenuTrocaIdioma(idioma, menuAnterior);
            default -> new MenuInicial(idioma);
        };
    }

    public static Menu criarMenuAdicionarNovoIdiomaOrientacao(TipoMenu tipoMenu, Menu menuAnterior, OrientacaoDto orientacao, IdiomaOrientacao idiomaOrientacao, IdiomaImplementacao idioma) {
        return switch (tipoMenu) {
            case ADICAO_ORIENTACAO -> new MenuAdicionarIdiomaOrientacao(idioma, criarOrientacaoService(), menuAnterior, orientacao, idiomaOrientacao);
            default -> new MenuInicial(idioma);
        };
    }
}
