package aplication.implementacoes;

import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;

public interface IdiomaImplementacao {

	IdiomaOrientacao obterIdiomaOrientacao();

	String mostrarMenuInicial(Scanner input);

	UsuarioDto mostrarMenuLogin(Scanner input);

	UsuarioDto  mostrarMenuCadastro(Scanner input);

	String mostrarMenuGeral(Scanner input);

	String mostrarMenuOpcoesOrientacao(Scanner input);

	List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception;

	String mostrarMenuOrientacaoDisponiveis(Scanner input, String listaFormatada, String palavraPesquisada);

	String mostrarMenuErro(Scanner input, String mensagemErro);

	String mostrarMenuAcerto(Scanner input, String mensagemAcerto);

	String mostrarMenuPesquisaOrientacao(Scanner input);

	String pegarMensagemErroLogin();

	String pegarMensagemErroLoginUsuario();

	String pegarMensagemErroLoginSenha();

	String pegarMensagemLoginConcluido();

	String pegarMensagemErroCadastroUsuarioExistente();

	String pegarMensagemErroCadastroSenhaPequena();

	String pegarMensagemErroCadastroSenhaSemMaiscula();

	String pegarMensagemErroCadastroSenhaSemCaracterEspecial();

	String pegarMensagemCadastroConcluido();

	String pegarMensangemAdicaoConcluida();

	String pegarMensangemAdicaoFalhada();

	String pegarMensagemEdicaoConcluida();

	String pegarMensagemEdicaoFalha();

	String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao);

	String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientacoes);

	String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados);

	String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado);

	String pegarMensagemOrientacoesNaoEncontrada();

	String pegarIdiomaDisponivel();

	String pegarIdiomaIndisponivel();

	String pegarMensagemErro();

	OrientacaoDto mostrarMenuAdicionarOrientacao(Scanner input, TipoOrientacao tipoOrientacao,
			IdiomaOrientacao idiomaOrientacao);

	String pegarMensagemRemoverComSucessoOrientacao();

	String pegarMensagemErroAoRemoverOrientacao();

	OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao,
			TipoOrientacao tipoOrientacao) throws Exception;

	String mostrarMenuConfirmarApagarOrientacao(Scanner input) throws SairMenuException;

	String mostrarMenuFiltro(Scanner input);

	String mostrarMenuApagarFiltro(Scanner input, String tipoFiltro, String filtrosDisponiveis);

	String mostrarMenuVisualizarTiposFiltros(Scanner input, String tipoOrientacoesDisponiveis);

	String pegarMensagemEntradaInvalida();

	String mostrarMenuVisualizarFiltros(Scanner input, String filtrosDisponiveis, String tipoFiltro);

	String mostrarMenuVisualizarFiltrosDisponiveis(Scanner input, String filtroDisponiveis, String tipoFiltro);

	String pegarMensagemAdicionadoNovoFiltro(Scanner input);
	
	String pegarMensagemFalhaAdicionarFiltro(Scanner input);
	
	String mostrarMenuEditarOrientacao(Scanner input);
	
	String mostrarMenuMudarTituloOrientacao(Scanner input, String tituloAntigo);
	
	String mostrarMenuMudarConteudoOrientacao(Scanner input, String conteudoAntigo);
	
	String mostrarMenuMudarTipoOrientacao(Scanner input, String tipoAntigo, String tiposFormatados);
	
	String mostrarMenuMudarIdiomaOrientacao(Scanner input, String idiomaAntigo, String idiomasFormatados);
	
	String mostrarMenuConfirmarEdicao(Scanner input);
	
	String pegarMensagemIdiomaNaoDisponivel();
	
	String mostrarMenuConfirmarMudancaTipo(Scanner input);
	
	void mostrarMenuAlteradoAtributoComSucesso();
	
	String pegarMensagemFiltroJaCriado();
	
	String pegarMensagemEmailInvalidoJaUsado();
	
	String pegarMensagemUsuarioInvalidoLimiteDeCaracters();
	
	String pegarMensagemUsuarioInvalidoEmBranco();
	
	String pegarMensagemEmailComSintaxeIncorreta();

	String mostrarMenuOpcaoApagarOrientacao(Scanner input) throws SairMenuException;
	
	String pegarMensagemVoltandoMenu();
	
	String pegarMensagemNivelDeAcessoInsuficiente();
	
}
