package service.commandos;

import Dominio.NivelAcesso;

public abstract class Comando {
	protected int idUsuario;

	public Comando(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int pegarIdUsuario() {
		return idUsuario;
	}

	public abstract void executarComando();

	public abstract RegistroComando devolverRegistroComando();

	public abstract TiposComando pegarTipo();

	public abstract RegistroComando voltarAcao();

	public abstract boolean validarNivelDeAcesso(NivelAcesso nivelAcesso);

}
