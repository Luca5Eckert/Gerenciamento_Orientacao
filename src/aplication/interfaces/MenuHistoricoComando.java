package aplication.interfaces;

import java.util.Scanner;

import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import infrastructure.dao.RegistroComandoDAO;
import service.SessaoUsuario;
import service.commandos.ComandoHistorico;
import service.commandos.ExecutadorComando;
import service.exceptions.ComandoException;
import service.exceptions.ComandoHistoricoException;
import service.exceptions.NivelDeAcessoInsuficienteException;

public class MenuHistoricoComando extends Menu{
    public static final String SAIR_MENU = "S";
    public static final String VOLTAR_COMANDO = "V";
    public static final String IR_PARA_FRENTE_COMANDO = "F";
    public static final String APAGAR_HISTORICO = "A";
    public static final String CONFIRMAR_APAGAR = "M";
    public static final String CANCELAR_ACAO = "C";

    private SessaoUsuario sessaoUsuario;
    private ExecutadorComando executadorComando;

    public MenuHistoricoComando(IdiomaImplementacao idiomaImplementacao, SessaoUsuario sessaoUsuario){
    	super(idiomaImplementacao);
    	this.sessaoUsuario = sessaoUsuario;
    	executadorComando = ExecutadorComando.criarExecutadorComando(this.sessaoUsuario, new RegistroComandoDAO());	    	
    }
    
    @Override 
    public void chamarMenu( Scanner input, MenuHistorico menuHistorico){
    	var comandoHistorico = sessaoUsuario.pegarHistoricoComandos();
    	
    	String historicoComandosUsuario = comandoHistorico.gerarHistorico(idiomaImplementacao);
    	String inputUsuario = idiomaImplementacao.mostrarMenuHistorico(input, historicoComandosUsuario);
    	String acao = inputUsuario.trim().toUpperCase();
    	
    
    	try {
    		switch(acao){
    		case SAIR_MENU -> menuHistorico.voltarMenu();
    		case VOLTAR_COMANDO -> voltarComando(comandoHistorico);
    		case IR_PARA_FRENTE_COMANDO -> irParaFrenteComando(comandoHistorico);
    		case APAGAR_HISTORICO -> apagarHistorico(input, comandoHistorico);
    		default -> imprimirMensagemErro(idiomaImplementacao.pegarMensagemEntradaInvalida());
    		}
    	} catch (ComandoHistoricoException che){ 
    		imprimirMensagemErro(idiomaImplementacao.pegarMensagemErroAoMexerNoHistorico());
    	} catch (ComandoException ce ){
    		imprimirMensagemErro(idiomaImplementacao.pegarMensagemErroComando());
    	}
    	
    }
    
    public void voltarComando(ComandoHistorico historicoComando){
			   try{
					   executadorComando.desfazerComando(historicoComando.pegarComandoAtual());
					   historicoComando.voltarComando();
			   } catch ( NivelDeAcessoInsuficienteException ndaie){
					   imprimirMensagemErro(idiomaImplementacao.pegarMensagemNivelDeAcessoInsuficiente());
			   }
			   
	  }
	  
    public void irParaFrenteComando(ComandoHistorico historicoComando){
			   try{
					   historicoComando.irParaFrenteComando();
					   executadorComando.refazerComando(historicoComando.pegarComandoAtual());
			   } catch ( NivelDeAcessoInsuficienteException ndaie){
					   imprimirMensagemErro(idiomaImplementacao.pegarMensagemNivelDeAcessoInsuficiente());
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