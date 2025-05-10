package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.IdiomaFactory;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;

public class MenuTrocaIdioma implements Menu {
	private IdiomaImplementacao idiomaImplementacao;
	private Menu menuAnterior;

	public MenuTrocaIdioma(IdiomaImplementacao idiomaImplementacao, Menu menuAnterior) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.menuAnterior = menuAnterior;
	}
	
	@Override
	public Menu chamarMenu(Scanner input) {
		String idiomaFormatado = IdiomaOrientacao.listaIdiomasFormatado(idiomaImplementacao);
		
		String opcaoSelecionado = idiomaImplementacao.mostrarMenuTrocarIdioma(input, idiomaFormatado);
		
		return processarOpcao(opcaoSelecionado);
	}
	
	public Menu processarOpcao(String opcaoSelecionado) {
		return switch(opcaoSelecionado.toUpperCase().trim()) {
		case "S" -> MenuFactory.criarMenu(TipoMenu.GERAL);
		default -> processarMudanca(opcaoSelecionado);
		};
	}
	
	public Menu processarMudanca(String entradaUsuario) {
        try {
            int indiceIdioma = parseEntradaUsuario(entradaUsuario);
            IdiomaOrientacao idiomaEscolhido = IdiomaOrientacao.pegarIdioma(indiceIdioma);
            IdiomaImplementacao novaImplementacao = atualizarSessaoIdioma(idiomaEscolhido);

            atualizarIdiomaMenuAnterior(novaImplementacao);

            String mensagem = construirMensagemSucesso(idiomaEscolhido, novaImplementacao);
            return MenuFactory.criarMenuResultado(TipoMenu.CERTO, menuAnterior, mensagem);

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

    private IdiomaImplementacao atualizarSessaoIdioma(IdiomaOrientacao idiomaEscolhido) {
        IdiomaImplementacao novaImplementacao = IdiomaFactory.pegarIdiomaImplementacao(idiomaEscolhido);
        SessaoUsuario.definirIdioma(novaImplementacao);
        return novaImplementacao;
    }

    private void atualizarIdiomaMenuAnterior(IdiomaImplementacao novaImplementacao) {
        menuAnterior.mudarIdioma(novaImplementacao);
    }

    private String construirMensagemSucesso(IdiomaOrientacao idiomaEscolhido, IdiomaImplementacao implementacao) {
        IdiomaOrientacao idiomaUsuarioAtual = implementacao.obterIdiomaOrientacao();
        String nomeIdioma = idiomaEscolhido.getNomePorIdioma(idiomaUsuarioAtual);
        return implementacao.pegarMensagemTrocaDeIdiomaBemSucedida(nomeIdioma);
    }
	
	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
