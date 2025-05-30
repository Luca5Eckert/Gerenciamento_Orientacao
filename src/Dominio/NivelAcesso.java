package Dominio;

public enum NivelAcesso {
	CONVIDADO(1),
	ALTERADOR(2),
	ADMINISTRADOR(3);
	
	private int nivelAcesso;
	
	NivelAcesso(int nivelAcesso) {
		this.setNivelAcesso(nivelAcesso);
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

}
