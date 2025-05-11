package aplication.implementacoes;

import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import dtos.OrientacaoDto;
import dtos.UsuarioDto;
import service.filtros.GerenciadorFiltrosOrientacao;

public interface IdiomaImplementacao {

	IdiomaOrientacao obterIdiomaOrientacao();

	String mostrarMenuInicial(Scanner input);

	UsuarioDto mostrarMenuLogin(Scanner input);

	UsuarioDto mostrarMenuCadastro(Scanner input);

	String mostrarMenuGeral(Scanner input);

	String mostrarMenuFiltro(Scanner input, GerenciadorFiltrosOrientacao gerenciador);

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

	String pegarNomeIdioma(IdiomaOrientacao idiomaOrientacao);

	String mostrarOrientacao(Scanner input, OrientacaoDto orientacao, String idiomasOrientacoes);

	String pegarFiltroIdioma();
	
	String pegarFiltroTipo();

	String mostrarMenuTrocarIdioma(Scanner input, String idiomaFormatados);
	
	String pegarMensagemTrocaDeIdiomaBemSucedida(String idiomaAlterado);
	
	String pegarMensagemOrientacoesNaoEncontrada();
	
}
