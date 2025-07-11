package service.commandos;

import java.sql.SQLException;

import service.SessaoUsuario;
import service.exceptions.ComandoHistoricoException;
import service.exceptions.NivelDeAcessoInsuficienteException;
import infrastructure.dao.RegistroComandoDAO;

public class ExecutadorComando {

    private SessaoUsuario sessaoUsuario;
    private RegistroComandoDAO registroComandoDAO;

    public ExecutadorComando(SessaoUsuario sessaoUsuario, RegistroComandoDAO registroComandoDAO) {
        this.sessaoUsuario = sessaoUsuario;
        this.registroComandoDAO = registroComandoDAO;
    }

    public static ExecutadorComando criarExecutadorComando(SessaoUsuario sessaoUsuario,
            RegistroComandoDAO registroComandoDAO) {
        return new ExecutadorComando(sessaoUsuario, registroComandoDAO);
    }

    public void aplicarComando(Comando comando) {
        if (comando.validarNivelDeAcesso(sessaoUsuario.pegarNivelAcesso())) {
            comando.executarComando();
            salvarRegistroComando(comando.devolverRegistroComando());
            sessaoUsuario.salvarComando(comando);
            return;
        }
        throw new NivelDeAcessoInsuficienteException();
    }

    public void desfazerComando(Comando comando) {
        if (comando == null) {
            throw new ComandoHistoricoException("Nenhum comando válido para desfazer.");
        }

        if (comando.validarNivelDeAcesso(sessaoUsuario.pegarNivelAcesso())) {
            RegistroComando registro = comando.voltarAcao();
            salvarRegistroComando(registro);
            return;
        }

        throw new NivelDeAcessoInsuficienteException();
    }

    public void refazerComando(Comando comando) {
        if (comando == null) {
            throw new ComandoHistoricoException("Nenhum comando válido para refazer.");
        }

        if (comando.validarNivelDeAcesso(sessaoUsuario.pegarNivelAcesso())) {
            comando.refazerAcao();
            salvarRegistroComando(comando.devolverRegistroComando());
            return;
        }

        throw new NivelDeAcessoInsuficienteException();
    }


    private void salvarRegistroComando(RegistroComando registroComando) {
        try {
            registroComandoDAO.salvarRegistroComando(registroComando);
        } catch (SQLException se) {
            System.err.println("Não foi possível salvar registro: " + se.getMessage());
        }
    }
}
