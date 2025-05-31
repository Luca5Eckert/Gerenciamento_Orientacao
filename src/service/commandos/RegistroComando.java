package service.commandos;

import Dominio.IdiomaOrientacao;

public class RegistroComando {
	private int idUsuario;
	private String idOrientacao;
	private IdiomaOrientacao idiomaOrientacao;
	private TiposComando tipoComando;
	
	public RegistroComando(int idUsuario, String idOrientacao, IdiomaOrientacao idiomaOrientacao, TiposComando tipoComando) {
		this.idUsuario = idUsuario;
		this.idOrientacao = idOrientacao;
		this.setIdiomaOrientacao(idiomaOrientacao);
		this.tipoComando = tipoComando;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdOrientacao() {
		return idOrientacao;
	}

	public void setIdOrientacao(String idOrientacao) {
		this.idOrientacao = idOrientacao;
	}

	public TiposComando getTipoComando() {
		return tipoComando;
	}

	public void setTipoComando(TiposComando tipoComando) {
		this.tipoComando = tipoComando;
	}

	public IdiomaOrientacao getIdiomaOrientacao() {
		return idiomaOrientacao;
	}

	public void setIdiomaOrientacao(IdiomaOrientacao idiomaOrientacao) {
		this.idiomaOrientacao = idiomaOrientacao;
	}
}
