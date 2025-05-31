package service.commandos;

import java.sql.SQLException;

import aplication.implementacoes.IdiomaImplementacao;
import infrastructure.dao.RegistroComandoDAO;
import service.SessaoUsuario;
import service.exceptions.NivelDeAcessoInsuficienteException;

public class ExecutadorComando {

	private Comando comando;
	private SessaoUsuario sessaoUsuario;
	private RegistroComandoDAO registroComandoDAO;

	public ExecutadorComando(Comando comando, SessaoUsuario sessaoUsuario, RegistroComandoDAO registroComandoDAO) {
		this.comando = comando;
		this.sessaoUsuario = sessaoUsuario;
		this.registroComandoDAO = registroComandoDAO;
	}

	public static ExecutadorComando criarExecutadorComando(Comando comando, SessaoUsuario sessaoUsuario,
			RegistroComandoDAO registroComandoDAO) {
				return new ExecutadorComando(comando, sessaoUsuario, registroComandoDAO);
	}
	
	public void aplicarComando(IdiomaImplementacao idiomaImplementacao) {
		
		if(comando.validarNivelDeAcesso(sessaoUsuario)) {
			comando.executarComando();
			salvarRegistroComando();
			return;
		}
		
		throw new NivelDeAcessoInsuficienteException(idiomaImplementacao.pegarMensagemNivelDeAcessoInsuficiente());
	}
	
	private void salvarRegistroComando() {
		try {
			registroComandoDAO.salvarRegistroComando(comando.devolverRegistroComando());			
		} catch ( SQLException se) {
			System.out.println(" Não foi possivel salvar registro: " + se.getMessage());
		}
	}

}
