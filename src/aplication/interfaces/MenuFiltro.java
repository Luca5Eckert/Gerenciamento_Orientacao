package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import service.filtros.GerenciadorFiltrosOrientacao;

public class MenuFiltro implements Menu {

    private GerenciadorFiltrosOrientacao gerenciadorFiltro;

    public MenuFiltro(GerenciadorFiltrosOrientacao gerenciadorFiltro) {
        this.gerenciadorFiltro = gerenciadorFiltro;
    }

    @Override
    public Menu chamarMenu(IdiomaImplementacao idiomaImplementacao, Scanner input) {
        boolean menuFiltroAtivo = true;

        while (menuFiltroAtivo) {
            String opcao = idiomaImplementacao.mostrarMenuFiltro(input, gerenciadorFiltro);
            menuFiltroAtivo = processarOpcaoEscolhida(opcao);
        }

        return MenuFactory.criarMenuComFiltros(TipoMenu.EXIBIR_ORIENTACOES, gerenciadorFiltro);
    }

    public boolean processarOpcaoEscolhida(String opcao) {
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
        return gerenciadorFiltro.definirFiltroIdioma(idioma);
    }



    private boolean definirFiltroTipo(TipoOrientacao tipo) {
        gerenciadorFiltro.definirFiltroTipo(tipo);
        return true;
    }


}
