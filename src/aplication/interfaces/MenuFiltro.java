package aplication.interfaces;

import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.filtros.FiltroOrientacao;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.filtros.TipoFiltro;
import service.formatacao.FormatacaoNumerarLista;

public class MenuFiltro implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private FormatacaoNumerarLista formatacaoLista;
	private GerenciadorFiltrosOrientacao gerenciadorFiltro;

	public MenuFiltro(IdiomaImplementacao idiomaImplementacao, FormatacaoNumerarLista formatacaoLista,
			GerenciadorFiltrosOrientacao gerenciadorFiltro) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.formatacaoLista = formatacaoLista;
		this.gerenciadorFiltro = gerenciadorFiltro;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String opcao = idiomaImplementacao.mostrarMenuFiltro(input);

		processarOpcao(opcao, input, menuHistorico);
	}

	public void processarOpcao(String opcao, Scanner input, MenuHistorico menuHistorico) {
		switch (opcao.trim().toUpperCase()) {
		case "V" -> menuHistorico.voltarMenu();
		case "1" -> visualizarFiltros(input);
		case "2" -> definirFiltro(input);
		case "3" -> menuHistorico.definirProximoMenu(MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao));
		}
	}

	private void visualizarFiltros(Scanner input) {
		List<TipoFiltro> tiposExistentes = gerenciadorFiltro.pegarTiposDeFiltros();
		
		String tipoOrientacoesDisponiveis = formatacaoLista.formatarTiposDeFiltro(tiposExistentes, idiomaImplementacao);
		boolean visualizarFiltros = true;
		
		do {
			String opcao = idiomaImplementacao.mostrarMenuVisualizarFiltros(input, tipoOrientacoesDisponiveis);
			
			switch(opcao.trim().toUpperCase()) {
			case "V" -> visualizarFiltros = false;
			default -> visualizarTipoFiltroAtivados(input, opcao);
			}
		} while (visualizarFiltros);
	}


	private Object visualizarTipoFiltroAtivados(Scanner input, String opcao) {
		try {
			int indexTipoFiltro = Integer.parseInt(opcao);
			
			FiltroTipo tipoFiltro = TipoFiltro.pegarTipoFiltroPorIndex(tipoFiltro);
			
		} catch (NumberFormatException npe ) {
			System.err.println(idiomaImplementacao.pegarMensagemEntradaInvalida());
		}
		return null;
	}

	public void definirFiltro(Scanner input) {
		List<TipoFiltro> tiposExistentes = TipoFiltro.listarTipoFiltros();
		
		String tipoOrientacoesDisponiveis = formatacaoLista.formatarTiposDeFiltro(tiposExistentes, idiomaImplementacao);
		boolean visualizarFiltros = true;
		
		do {
			String opcao = idiomaImplementacao.mostrarMenuVisualizarFiltros(input, tipoOrientacoesDisponiveis);
			
			switch(opcao.trim().toUpperCase()) {
			case "V" -> visualizarFiltros = false;
			default -> visualizarFiltrosDefinir();
			}
		} while (visualizarFiltros);
	}

	private Object visualizarFiltrosDefinir() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletarFiltro(Scanner input) {


	}

	public boolean processarFiltroEscolhido(String opcao) {
		switch (opcao.trim().toUpperCase()) {
		case "4":
			return false;
		case "P":
			return adicionarFiltroIdioma(IdiomaOrientacao.PORTUGUES);
		case "I":
			return adicionarFiltroIdioma(IdiomaOrientacao.INGLES);
		case "E":
			return adicionarFiltroIdioma(IdiomaOrientacao.ESPANHOL);
		case "A":
			return adicionarFiltroIdioma(IdiomaOrientacao.ALEMAO);
		case "M":
			return definirFiltroTipo(TipoOrientacao.MANUAL_OPERACAO);
		case "S":
			return definirFiltroTipo(TipoOrientacao.PROCEDIMENTO_SEGURANCA);
		case "R":
			return definirFiltroTipo(TipoOrientacao.MANUTENCAO_REPAROS);
		case "D":
			return definirFiltroTipo(TipoOrientacao.TESTES_DIAGNOSTICO);
		case "C":
			return definirFiltroTipo(TipoOrientacao.MANUAL_CONDUTA_OPERACOES);
		default:
			return true;
		}
	}

	private boolean adicionarFiltroIdioma(IdiomaOrientacao idioma) {
		gerenciadorFiltro.adicionarFiltro(TipoFiltro.IDIOMA, idioma);
		return true;
	}

	private boolean definirFiltroTipo(TipoOrientacao tipo) {
		gerenciadorFiltro.adicionarFiltro(TipoFiltro.TIPO, tipo);
		return true;
	}

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		
	}
}
