package Dominio;

import java.util.UUID;

public class OrientacaoId {
	private String idOrientacao;
	private IdiomaOrientacao idiomaOrientacao;
	
	public OrientacaoId(String idOrientacao, IdiomaOrientacao idiomaOrientacao) {
		this.idOrientacao = idOrientacao;
		this.idiomaOrientacao = idiomaOrientacao;
	}
	
	public OrientacaoId(IdiomaOrientacao idiomaOrientacao) {
		this.idOrientacao = UUID.randomUUID().toString();
		this.idiomaOrientacao = idiomaOrientacao;
	}

	public OrientacaoId() {
	}

	public String getIdOrientacao() {
		return idOrientacao;
	}

	public void setIdOrientacao(String idOrientacao) {
		this.idOrientacao = idOrientacao;
	}

	public IdiomaOrientacao getIdiomaOrientacao() {
		return idiomaOrientacao;
	}

	public void setIdiomaOrientacao( IdiomaOrientacao idiomaOrientacao) {
		this.idiomaOrientacao = idiomaOrientacao;
	}
}
