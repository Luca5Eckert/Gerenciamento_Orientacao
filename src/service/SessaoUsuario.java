package service;

import Dominio.NivelAcesso;

public class SessaoUsuario {

	private int idUsuario;
	private NivelAcesso nivelDeAcesso;

	public SessaoUsuario(int idUsuario, NivelAcesso nivelDeAcesso) {
		this.idUsuario = idUsuario;
		this.nivelDeAcesso = nivelDeAcesso;
	}

	public int pegarIdUsuario() {
		return idUsuario;
	}
	
	public int pegarNivelAcesso() {
		return nivelDeAcesso.getNivelAcesso();
	}
	

}
