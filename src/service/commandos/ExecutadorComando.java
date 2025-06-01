package service.commandos;

import java.sql.SQLException;

import aplication.implementacoes.IdiomaImplementacao;
import infrastructure.dao.RegistroComandoDAO;
import service.exceptions.NivelDeAcessoInsuficienteException;

public class ExecutadorComando {

	private Comando comando;
	private RegistroComandoDAO registroComandoDAO;

	public ExecutadorComando(Comando comando, RegistroComandoDAO registroComandoDAO) {
		this.comando = comando;
		this.registroComandoDAO = registroComandoDAO;
	}

	public static ExecutadorComando criarExecutadorComando(Comando comando,
			RegistroComandoDAO registroComandoDAO) {
				return new ExecutadorComando(comando, registroComandoDAO);
	}
	
	public void aplicarComando(IdiomaImplementacao idiomaImplementacao) {
		
		if(comando.validarNivelDeAcesso()) {
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
