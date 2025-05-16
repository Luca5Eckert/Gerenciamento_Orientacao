package aplication.interfaces;

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

    public MenuFiltro(IdiomaImplementacao idiomaImplementacao, FormatacaoNumerarLista formatacaoLista, GerenciadorFiltrosOrientacao gerenciadorFiltro) {
        this.idiomaImplementacao = idiomaImplementacao;
        this.formatacaoLista = formatacaoLista;
    	this.gerenciadorFiltro = gerenciadorFiltro;
    }

    @Override
    public Menu chamarMenu(Scanner input,  MenuHistorico menuHistorico) {
        boolean menuFiltroAtivo = true;

        String opcao = idiomaImplementacao.mostrarMenuFiltro(input, "sada");
        
        processarOpcao(opcao, input);
        
        return MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro, idiomaImplementacao);
    }
    
    public Menu processarOpcao(String opcao, Scanner input) {
    	return switch(opcao.trim().toUpperCase()) {
    	case "D" -> deletarFiltro(input);
    	case "1" -> definirFiltro(input, opcao);
		default -> this;
    	};
    }
    
    public Menu definirFiltro(Scanner input, String opcaoEscolhida) {
    	String opcaoFiltro = idiomaImplementacao.mostrarMenuDefinirFiltro(input, opcaoEscolhida);
    	processarFiltroEscolhido(opcaoFiltro);
    	return this;
    }
    
    public Menu deletarFiltro(Scanner input) {
        String filtrosFormatados = "";

    	String filtroEscolhido = idiomaImplementacao.mostrarMenuApagarFiltro(input, filtrosFormatados);
    	try {
    		int numeroFiltroEscolhido = Integer.parseInt(filtroEscolhido);
    		
    	} catch(NumberFormatException nfe) {
    		return this;
    	}
		return null;
    }

    public boolean processarFiltroEscolhido(String opcao) {
        switch (opcao.trim().toUpperCase()) {
            case "4": return false; 
            case "P": return adicionarFiltroIdioma(IdiomaOrientacao.PORTUGUES);
            case "I": return adicionarFiltroIdioma(IdiomaOrientacao.INGLES);
            case "E": return adicionarFiltroIdioma(IdiomaOrientacao.ESPANHOL);
            case "A": return adicionarFiltroIdioma(IdiomaOrientacao.ALEMAO);
            case "M": return definirFiltroTipo(TipoOrientacao.MANUAL_OPERACAO);
            case "S": return definirFiltroTipo(TipoOrientacao.PROCEDIMENTO_SEGURANCA);
            case "R": return definirFiltroTipo(TipoOrientacao.MANUTENCAO_REPAROS);
            case "D": return definirFiltroTipo(TipoOrientacao.TESTES_DIAGNOSTICO);
            case "C": return definirFiltroTipo(TipoOrientacao.MANUAL_CONDUTA_OPERACOES);
            default: return true;
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
    
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}



}
