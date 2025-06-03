package service.commandos;

import java.sql.SQLException;

import aplication.implementacoes.IdiomaImplementacao;
import infrastructure.dao.RegistroComandoDAO;
import service.SessaoUsuario;
import service.exceptions.NivelDeAcessoInsuficienteException;

public class ExecutadorComando {

	private SessaoUsuario sessaoUsuario;
	private Comando comando;
	private RegistroComandoDAO registroComandoDAO;

	public ExecutadorComando(SessaoUsuario sessaoUsuario, Comando comando, RegistroComandoDAO registroComandoDAO) {
		this.sessaoUsuario = sessaoUsuario;
		this.comando = comando;
		this.registroComandoDAO = registroComandoDAO;
	}

	public static ExecutadorComando criarExecutadorComando(SessaoUsuario sessaoUsuario, Comando comando,
			RegistroComandoDAO registroComandoDAO) {
		return new ExecutadorComando(sessaoUsuario, comando, registroComandoDAO);
	}

	public void aplicarComando(IdiomaImplementacao idiomaImplementacao) {

		if (comando.validarNivelDeAcesso(sessaoUsuario.pegarNivelAcesso())) {
			comando.executarComando();
			salvarRegistroComando();
			sessaoUsuario.salvarComando(comando);
			return;
		}

		throw new NivelDeAcessoInsuficienteException(idiomaImplementacao.pegarMensagemNivelDeAcessoInsuficiente());
	}

	private void salvarRegistroComando() {
		try {
			registroComandoDAO.salvarRegistroComando(comando.devolverRegistroComando());
		} catch (SQLException se) {
			System.out.println(" Não foi possivel salvar registro: " + se.getMessage());
		}
	}

}
