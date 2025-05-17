package aplication.implementacoes;

import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public interface IdiomaImplementacao {

	IdiomaOrientacao obterIdiomaOrientacao();

	String mostrarMenuInicial(Scanner input);

	UsuarioDto mostrarMenuLogin(Scanner input);

	UsuarioDto mostrarMenuCadastro(Scanner input);

	String mostrarMenuGeral(Scanner input);

	String mostrarMenuOpcoesOrientacao(Scanner input);

	List<OrientacaoDto> mostrarMenuCriarOrientacao(Scanner input) throws Exception;

	OrientacaoDto mostrarMenuEditarOrientacao(OrientacaoDto orientacaoDto, Scanner input);

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
	
	OrientacaoDto mostrarMenuAdicionarOrientacao(Scanner input, TipoOrientacao tipoOrientacao, IdiomaOrientacao idiomaOrientacao);
	
	String pegarMensagemRemoverComSucessoOrientacao();
	
	String pegarMensagemErroAoRemoverOrientacao();
	
	OrientacaoDto mostrarMenuAdicionarNovoIdiomaOrientacao(Scanner input, IdiomaOrientacao idiomaOrientacao, TipoOrientacao tipoOrientacao) throws Exception;

	String mostrarMenuConfirmarApagarOrientacao(Scanner input) throws SairMenuException;
	
	String mostrarMenuFiltro(Scanner input);
	
	String mostrarMenuDefinirFiltro(Scanner input, String opcaoEscolhida);
	
	String mostrarMenuApagarFiltro(Scanner input, String filtrosDisponiveis);

	String mostrarMenuVisualizarTiposFiltros(Scanner input, String tipoOrientacoesDisponiveis);
	
	String pegarMensagemEntradaInvalida();
	
	String mostrarMenuVisualizarFiltros(Scanner input, String filtrosDisponiveis, String tipoFiltro);
	
}
