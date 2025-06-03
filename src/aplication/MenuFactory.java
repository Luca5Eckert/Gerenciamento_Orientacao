package aplication;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.*;
import dtos.OrientacaoDto;
import repositorio.UsuarioRepositorio;
import service.OrientacaoService;
import service.SessaoUsuario;
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
		filtroOrientacaoIdioma.adicionarFiltro(idiomaOrientacao.name());

		gerenciador.adicionarTipoFiltro(TipoFiltro.IDIOMA, filtroOrientacaoIdioma);

		return gerenciador;
	}

	public static Menu criarMenu(TipoMenu tipoMenu, IdiomaImplementacao idioma) {
		return switch (tipoMenu) {
		case INICIO -> new MenuInicial(idioma);
		case CADASTRO -> new MenuCadastro(idioma, criarUsuarioService(idioma));
		case LOGIN -> new MenuLogin(idioma, criarUsuarioService(idioma));
		default -> new MenuInicial(idioma);
		};
	}

	public static Menu criarMenuComSessao(TipoMenu tipoMenu, IdiomaImplementacao idioma, SessaoUsuario sessaoUsuario) {
		return switch (tipoMenu) {
		case GERAL -> new MenuGeral(idioma, sessaoUsuario);
		case ADICAO_ORIENTACAO -> new MenuAdicaoOrientacao(idioma, criarOrientacaoService(), sessaoUsuario);
		case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), criarGerenciadorFiltro(idioma),
				new FormatacaoNumerarLista(), idioma, sessaoUsuario);
		case FILTRO_GERAL -> new MenuFiltroGeral(idioma, criarGerenciadorFiltro(idioma), sessaoUsuario);
		case HISTORICO_COMANDOS -> new MenuHistoricoComando(idioma, sessaoUsuario);
		default -> new MenuGeral(idioma, sessaoUsuario);
		};
	}

	public static Menu criarMenuComFiltros(TipoMenu tipoMenu, GerenciadorFiltrosOrientacao gerenciadorFiltros,
			IdiomaImplementacao idioma, SessaoUsuario sessaoUsuario) {
		return switch (tipoMenu) {
		case FILTRO_GERAL -> new MenuFiltroGeral(idioma, gerenciadorFiltros, sessaoUsuario);
		case DEFINIR_FILTRO ->
			new MenuDefinirFiltro(idioma, gerenciadorFiltros, new FormatacaoNumerarLista(), sessaoUsuario);
		case VISUALIZAR_FILTRO ->
			new MenuExibirFiltros(idioma, gerenciadorFiltros, new FormatacaoNumerarLista(), sessaoUsuario);
		case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), gerenciadorFiltros,
				new FormatacaoNumerarLista(), idioma, sessaoUsuario);
		case PESQUISA_ORIENTACAO -> new MenuPesquisaOrientacao(idioma, gerenciadorFiltros, sessaoUsuario);
		default -> new MenuGeral(idioma, sessaoUsuario);
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
			IdiomaImplementacao idiomaImplementacao, SessaoUsuario sessaoUsuario) {

		return switch (tipoMenu) {
		case APAGAR_ORIENTACAO ->
			new MenuApagarOrientacao(idiomaImplementacao, criarOrientacaoService(), orientacaoDto, sessaoUsuario);
		case MOSTRAR_ORIENTACAO -> new MenuVisualizarOrientacao(idiomaImplementacao, orientacaoDto,
				criarOrientacaoService(), new FormatacaoListaComDivisoria(), sessaoUsuario);
		case EDICAO_ORIENTACAO ->
			new MenuEditarOrientacao(idiomaImplementacao, orientacaoDto, criarOrientacaoService(), sessaoUsuario);

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
			OrientacaoDto orientacao, IdiomaOrientacao idiomaOrientacao, IdiomaImplementacao idioma,
			SessaoUsuario sessaoUsuario) {
		return switch (tipoMenu) {
		case ADICAO_ORIENTACAO -> new MenuAdicionarIdiomaOrientacao(idioma, criarOrientacaoService(), menuAnterior,
				orientacao, idiomaOrientacao, sessaoUsuario);
		default -> new MenuInicial(idioma);
		};
	}
}
