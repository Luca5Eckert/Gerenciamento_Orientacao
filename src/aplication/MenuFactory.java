package aplication;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.*;
import dtos.OrientacaoDto;
import repositorio.UsuarioRepositorio;
import service.OrientacaoService;
import service.UsuarioService;
import service.filtros.FiltroFactory;
import service.filtros.FiltroOrientacao;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.filtros.TipoFiltro;
import service.formatacao.FormatacaoListaComDivisoria;
import service.formatacao.FormatacaoNumerarLista;

public class MenuFactory {

	public static UsuarioService criarUsuarioService(IdiomaImplementacao idioma) {
		return new UsuarioService(new UsuarioRepositorio(), idioma);
	}

	public static OrientacaoService criarOrientacaoService() {
		return new OrientacaoService();
	}

	@SuppressWarnings("unchecked")
	public static GerenciadorFiltrosOrientacao criarGerenciadorFiltro(IdiomaImplementacao idioma) {
	    var gerenciador = new GerenciadorFiltrosOrientacao();
	    
	    var filtroGenerico = FiltroFactory.criarFiltro(TipoFiltro.IDIOMA);
	    var filtroOrientacaoIdioma = (FiltroOrientacao<IdiomaOrientacao>) filtroGenerico;

	    var idiomaOrientacao = idioma.obterIdiomaOrientacao();
	    filtroOrientacaoIdioma.adicionarFiltro(idiomaOrientacao);

	    gerenciador.adicionarTipoFiltro(TipoFiltro.IDIOMA, filtroOrientacaoIdioma);

	    return gerenciador;
	}


	public static Menu criarMenu(TipoMenu tipoMenu, IdiomaImplementacao idioma) {
		return switch (tipoMenu) {
		case INICIO -> new MenuInicial(idioma);
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
				new FormatacaoNumerarLista(), idioma);
		case FILTRO_GERAL -> new MenuFiltroGeral(idioma, criarGerenciadorFiltro(idioma));
		default -> new MenuInicial(idioma);
		};
	}

	public static Menu criarMenuComFiltros(TipoMenu tipoMenu, GerenciadorFiltrosOrientacao gerenciadorFiltros,
			IdiomaImplementacao idioma) {
		return switch (tipoMenu) {
		case FILTRO_GERAL -> new MenuFiltroGeral(idioma, gerenciadorFiltros);
		case DEFINIR_FILTRO -> new MenuDefinirFiltro(idioma, gerenciadorFiltros, new FormatacaoNumerarLista());
		case VISUALIZAR_FILTRO -> new MenuExibirFiltros(idioma, gerenciadorFiltros, new FormatacaoNumerarLista());
		case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), gerenciadorFiltros,
				new FormatacaoNumerarLista(), idioma);
		case PESQUISA_ORIENTACAO -> new MenuPesquisaOrientacao(idioma, gerenciadorFiltros);
		default -> new MenuGeral(idioma);
		};
	}

	public static Menu criarMenuResultado(TipoMenu tipoMenu, Menu proximo, String mensagem,
			IdiomaImplementacao idiomaImplementacao) {
		return switch (tipoMenu) {
		case CERTO -> new MenuCerto(idiomaImplementacao, proximo, mensagem);
		case FALHA -> new MenuFalha(idiomaImplementacao, proximo, mensagem);
		default -> new MenuInicial(idiomaImplementacao);
		};
	}

	public static Menu criarMenuPesquisa(TipoMenu tipoMenu, OrientacaoDto orientacaoDto,
			IdiomaImplementacao idiomaImplementacao) {

		return switch (tipoMenu) {
		case MOSTRAR_ORIENTACAO -> new MenuVisualizarOrientacao(idiomaImplementacao, orientacaoDto,
				criarOrientacaoService(), new FormatacaoListaComDivisoria());
		case EDICAO_ORIENTACAO ->
			new MenuEditarOrientacao(orientacaoDto, idiomaImplementacao, criarOrientacaoService());
		default -> new MenuInicial(idiomaImplementacao);
		};
	}

	public static Menu criarMenuAlterarSistema(TipoMenu tipoMenu, Menu menuAnterior, IdiomaImplementacao idioma) {
		return switch (tipoMenu) {
		case ALTERAR_IDIOMA -> new MenuTrocaIdioma(idioma, menuAnterior);
		default -> new MenuInicial(idioma);
		};
	}

	public static Menu criarMenuAdicionarNovoIdiomaOrientacao(TipoMenu tipoMenu, Menu menuAnterior,
			OrientacaoDto orientacao, IdiomaOrientacao idiomaOrientacao, IdiomaImplementacao idioma) {
		return switch (tipoMenu) {
		case ADICAO_ORIENTACAO -> new MenuAdicionarIdiomaOrientacao(idioma, criarOrientacaoService(), menuAnterior,
				orientacao, idiomaOrientacao);
		default -> new MenuInicial(idioma);
		};
	}
}
