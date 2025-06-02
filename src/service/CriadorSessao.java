package service;

import java.sql.SQLException;

import Dominio.NivelAcesso;
import infrastructure.dao.RegistroLoginDAO;
import repositorio.UsuarioRepositorio;
import service.exceptions.usuario.LoginException;

public class CriadorSessao {

	private RegistroLoginDAO registradorLogin;

	public CriadorSessao() {
		this.registradorLogin = new RegistroLoginDAO();
	}

	public SessaoUsuario criarSessao(int idUsuario, UsuarioRepositorio repositorio) {
		NivelAcesso nivelAcesso = repositorio.pegarNivelPeloId(idUsuario);
		SessaoUsuario sessaoUsuario = new SessaoUsuario(idUsuario, nivelAcesso);
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
