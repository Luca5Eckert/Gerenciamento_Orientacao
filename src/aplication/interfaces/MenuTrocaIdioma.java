package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.IdiomaFactory;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;

public class MenuTrocaIdioma implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private Menu menuAnterior;

	public MenuTrocaIdioma(IdiomaImplementacao idiomaImplementacao, Menu menuAnterior) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.menuAnterior = menuAnterior;
	}
	
	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		String idiomaFormatado = IdiomaOrientacao.listaIdiomasFormatado(idiomaImplementacao);
		
		String opcaoSelecionado = idiomaImplementacao.mostrarMenuTrocarIdioma(input, idiomaFormatado);
		
		var proximoMenu = processarOpcao(opcaoSelecionado, menuHistorico);
		
		menuHistorico.definirProximoMenu(proximoMenu);
	}
	
	public Menu processarOpcao(String opcaoSelecionado, MenuHistorico menuHistorico) {
		return switch(opcaoSelecionado.toUpperCase().trim()) {
		case "S" -> menuHistorico.voltarMenu();
		default -> processarMudanca(opcaoSelecionado);
		};
	}
	
	public Menu processarMudanca(String entradaUsuario) {
        try {
            int indiceIdioma = parseEntradaUsuario(entradaUsuario);
            IdiomaOrientacao idiomaEscolhido = IdiomaOrientacao.pegarIdioma(indiceIdioma-1);
            IdiomaImplementacao novaImplementacao = pegarNovaImplementacao(idiomaEscolhido);

            atualizarIdiomaMenuAnterior(novaImplementacao);

            String mensagem = construirMensagemSucesso(idiomaEscolhido, novaImplementacao);
			return MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuAnterior, mensagem, novaImplementacao);

        } catch (NumberFormatException e) {
            System.err.println("Erro: entrada inválida.");
        } catch (Exception e) {
            System.err.println("Erro inesperado ao trocar o idioma: " + e.getMessage());
        }

        return this;
    }

    private int parseEntradaUsuario(String entrada) throws Exception {
    	int numeroOpcao = Integer.parseInt(entrada) - 1;
    	
    	if(numeroOpcao < IdiomaOrientacao.values().length) {
    		return numeroOpcao;
    	}
    	throw new NumberFormatException();
   
    }

    private IdiomaImplementacao pegarNovaImplementacao(IdiomaOrientacao idiomaEscolhido) {
    	return IdiomaFactory.pegarIdiomaImplementacao(idiomaEscolhido);
    }

    private void atualizarIdiomaMenuAnterior(IdiomaImplementacao novaImplementacao) {
    	menuAnterior.trocarIdioma(novaImplementacao);
        this.idiomaImplementacao = novaImplementacao;
    }

    private String construirMensagemSucesso(IdiomaOrientacao idiomaEscolhido, IdiomaImplementacao implementacao) {
        IdiomaOrientacao idiomaUsuarioAtual = implementacao.obterIdiomaOrientacao();
        String nomeIdioma = idiomaEscolhido.getNomePorIdioma(idiomaUsuarioAtual);
        return implementacao.pegarMensagemTrocaDeIdiomaBemSucedida(nomeIdioma);
    }
	

	@Override
	public void trocarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		
	}

}
