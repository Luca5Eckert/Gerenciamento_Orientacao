package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.NivelAcesso;
import infrastructure.dao.RegistroComandoDAO;
import infrastructure.dao.RegistroLoginDAO;
import repositorio.UsuarioRepositorio;
import service.commandos.Comando;
import service.commandos.ComandoHistorico;
import service.commandos.ExecutadorComando;
import service.exceptions.usuario.LoginException;

public class CriadorSessao {

	private RegistroLoginDAO registradorLogin;

	public CriadorSessao() {
		this.registradorLogin = new RegistroLoginDAO();
	}

	public SessaoUsuario criarSessao(int idUsuario, UsuarioRepositorio repositorio) {
		NivelAcesso nivelAcesso = repositorio.pegarNivelPeloId(idUsuario);
		List<Comando> historico = new ArrayList<>();
		SessaoUsuario sessaoUsuario = new SessaoUsuario(idUsuario, nivelAcesso, new ComandoHistorico(historico, new ExecutadorComando( new RegistroComandoDAO())));
		registrarSessaoLogin(sessaoUsuario);

		return sessaoUsuario;
	}

	public boolean registrarSessaoLogin(SessaoUsuario sessaoUsuario) {
		try {
			registradorLogin.registrarSessaoLogin(sessaoUsuario);
			return true;
		} catch (SQLException e) {
			throw new LoginException();
		}

	}
}
