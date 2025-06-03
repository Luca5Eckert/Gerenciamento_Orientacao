package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import service.SessaoUsuario;
import service.commandos.ComandoHistorico;
import service.exceptions.ComandoHistoricoException;

public class MenuHistoricoComando extends Menu{
    public static final String SAIR_MENU = "S";
    public static final String VOLTAR_COMANDO = "V";
    public static final String IR_PARA_FRENTE_COMANDO = "F";
    public static final String APAGAR_HISTORICO = "A";
    public static final String CONFIRMAR_APAGAR = "M";
    public static final String CANCELAR_ACAO = "C";

    private SessaoUsuario sessaoUsuario;

    public MenuHistoricoComando(IdiomaImplementacao idiomaImplementacao, SessaoUsuario sessaoUsuario){
    	super(idiomaImplementacao);
    	this.sessaoUsuario = sessaoUsuario;
    }
    
    @Override 
    public void chamarMenu( Scanner input, MenuHistorico menuHistorico){
    	var comandoHistorico = sessaoUsuario.pegarHistoricoComandos();
    	
    	String historicoComandosUsuario = comandoHistorico.gerarHistorico(idiomaImplementacao);
    	String inputUsuario = idiomaImplementacao.mostrarMenuHistorico(input, historicoComandosUsuario);
    	String comando = inputUsuario.trim().toUpperCase();
    	
    	try {
    		switch(comando){
    		case SAIR_MENU -> menuHistorico.voltarMenu();
    		case VOLTAR_COMANDO -> comandoHistorico.voltarComando();
    		case IR_PARA_FRENTE_COMANDO -> comandoHistorico.irParaFrenteComando();
    		case APAGAR_HISTORICO -> apagarHistorico(input, comandoHistorico);
    		default -> imprimirMensagemErro(idiomaImplementacao.pegarMensagemEntradaInvalida());
    		}
    	} catch (ComandoHistoricoException che){ 
    		imprimirMensagemErro(idiomaImplementacao.pegarMensagemErroAoMexerNoHistorico());
    	} catch (ComandoException ce ){
    		imprimirMensagemErro(idiomaImplementacao.pegarMensagemErroComando());
    	}
    	
    }
    
    public void apagarHistorico(Scanner input, ComandoHistorico comandoHistorico){
    	String inputUsuario = idiomaImplementacao.confirmarApagarHistorico(input);
    	String confirmacao = inputUsuario.trim().toUpperCase();
    	
    	switch(confirmacao){
    	case CONFIRMAR_APAGAR -> {
    		comandoHistorico.apagarHistorico();
    		System.out.println(idiomaImplementacao.pegarMensagemHistoricoApagado());
    	}
    	case CANCELAR_ACAO -> imprimirMensagemErro(idiomaImplementacao.pegarMensagemCanceladoAcao());
    	default -> imprimirMensagemErro(idiomaImplementacao.pegarMensagemEntradaInvalida());  
    	}
    }
    
    public void imprimirMensagemErro(String mensagem){
    	System.err.println(mensagem);
    }

}
