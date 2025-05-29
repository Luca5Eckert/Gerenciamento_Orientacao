package service.commandos;

public class RegistroComando {
	private int idUsuario;
	private String idOrientacao;
	private TiposComando tiposComandos;
	
	public RegistroComando(int idUsuario, String idOrientacao, TiposComando tiposComandos) {
		this.idUsuario = idUsuario;
		this.idOrientacao = idOrientacao;
		this.tiposComandos = tiposComandos;
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

	public TiposComando getTiposComandos() {
		return tiposComandos;
	}

	public void setTiposComandos(TiposComando tiposComandos) {
		this.tiposComandos = tiposComandos;
	}
}
