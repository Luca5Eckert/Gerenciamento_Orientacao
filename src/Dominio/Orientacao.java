package Dominio;

public class Orientacao {

	private OrientacaoId idOrientacao;

	private String titulo;
	private TipoOrientacao tipoOrientacao;
	private String conteudo;
	private int usuarioCriador;

	public Orientacao(OrientacaoId idOrientacao, String titulo, TipoOrientacao tipoOrientacao, String conteudo,
			IdiomaOrientacao idiomaOrientacao, int usuarioCriador) {
		this.idOrientacao = idOrientacao;
		this.titulo = titulo;
		this.tipoOrientacao = tipoOrientacao;
		this.conteudo = conteudo;
		this.usuarioCriador = usuarioCriador;
	}

	public Orientacao() {
	}

	public OrientacaoId getIdOrientacao() {
		return idOrientacao;
	}

	public void setIdOrientacao(OrientacaoId idOrientacao) {
		this.idOrientacao = idOrientacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoOrientacao getTipoOrientacao() {
		return tipoOrientacao;
	}

	public void setTipoOrientacao(TipoOrientacao tipoOrientacao) {
		this.tipoOrientacao = tipoOrientacao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public int getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(int idUsuario) {
		this.usuarioCriador = idUsuario;
	}

}