package service;

import java.sql.SQLException;

import infrastructure.dao.RegistroLoginDAO;
import service.exceptions.usuario.LoginException;

public class CriadorSessao {

	private RegistroLoginDAO registradorLogin;

	public CriadorSessao() {
		this.registradorLogin = new RegistroLoginDAO();
	}

	public SessaoUsuario criarSessao(int idUsuario) {
		SessaoUsuario sessaoUsuario = new SessaoUsuario(idUsuario);
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
