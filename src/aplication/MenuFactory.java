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
		case INICIO -> new MenuInicial(pegarIdioma());
		case FIM -> new MenuFinal();
		case GERAL -> new MenuGeral(pegarIdioma());
		default -> new MenuInicial(pegarIdioma());
		};
	}

	public static Menu criarMenuComIdioma(TipoMenu tipoMenu) {
		return switch (tipoMenu) {
		case CADASTRO -> new MenuCadastro(pegarIdioma(), criarUsuarioService());
		case LOGIN -> new MenuLogin(pegarIdioma(), criarUsuarioService());
		case ADICAO_ORIENTACAO -> new MenuAdicaoOrientacao(pegarIdioma(), criarOrientacaoService());
		case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), criarGerenciadorFiltro(),
				new FormatacaoListaOrientacao(), pegarIdioma());
		case FILTRO -> new MenuFiltro(pegarIdioma(), criarGerenciadorFiltro());
		default -> new MenuInicial(pegarIdioma());
		};
	}

	public static Menu criarMenuComFiltros(TipoMenu tipoMenu, GerenciadorFiltrosOrientacao gerenciadorFiltros) {
		return switch (tipoMenu) {
		case FILTRO -> new MenuFiltro(pegarIdioma(), gerenciadorFiltros);
		case EXIBIR_ORIENTACOES -> new MenuExibirOrientacoes(criarOrientacaoService(), gerenciadorFiltros,
				new FormatacaoListaOrientacao(), pegarIdioma());
		case PESQUISA_ORIENTACAO -> new MenuPesquisaOrientacao(pegarIdioma(), gerenciadorFiltros);
		default -> new MenuGeral(pegarIdioma());
		};
	}

	public static Menu criarMenuResultado(TipoMenu tipoMenu, Menu proximo, String mensagem) {
		return switch (tipoMenu) {
		case CERTO -> new MenuCerto(pegarIdioma(), proximo, mensagem);
		case FALHA -> new MenuFalha(pegarIdioma(), proximo, mensagem);
		default -> new MenuInicial(pegarIdioma());
		};
	}

	public static Menu criarMenuPesquisa(TipoMenu tipoMenu, OrientacaoDto orientacaoDto, Menu menu) {
		return switch (tipoMenu) {
		case MOSTRAR_ORIENTACAO ->
			new MenuVisualizarOrientacao(pegarIdioma(), orientacaoDto, menu, null, new FormatacaoListaOrientacao());
		default -> new MenuInicial(pegarIdioma());
		};
	}
}
