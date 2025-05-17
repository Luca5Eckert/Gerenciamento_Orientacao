package service.filtros;

import java.util.ArrayList;
import java.util.List;

import Dominio.IdiomaOrientacao;

public enum TipoFiltro {
	IDIOMA("Idioma", "Language", "Sprache", "idioma"), 
	TIPO("Tipo", "Type", "Typ", "Tipo");
	
	String nomePortugues;
	String nomeIngles;
	String nomeAlemao;
	String nomeEspanhol;
	
	private TipoFiltro(String nomePortugues, String nomeIngles, String nomeAlemao, String nomeEspanhol) {
		this.nomePortugues = nomePortugues;
		this.nomeIngles = nomeIngles;
		this.nomeAlemao = nomeAlemao;
		this.nomeEspanhol = nomeEspanhol;
	}
	
	public String pegarNome(IdiomaOrientacao idiomaOrientacao) {
		return switch(idiomaOrientacao) {
		case INGLES -> getNomeIngles();
		case PORTUGUES -> getNomePortugues();
		case ALEMAO -> getNomeAlemao();
		case ESPANHOL -> getNomeEspanhol();
		default -> getNomeIngles();
		};
	}

	public static List<TipoFiltro> listarTipoFiltros(){
		List<TipoFiltro> tiposFiltros = new ArrayList<>();
		
		for(TipoFiltro tipo : TipoFiltro.values()) {
			tiposFiltros.add(tipo);
		}
		
		return tiposFiltros;
	}
	
	public static TipoFiltro pegarTipoFiltroPorIndex(int index) {
		var listaFiltros = listarTipoFiltros();
		
		return listaFiltros.get(index-1);
	}
	
	public String getNomePortugues() {
		return nomePortugues;
	}

	public void setNomePortugues(String nomePortugues) {
		this.nomePortugues = nomePortugues;
	}

	public String getNomeIngles() {
		return nomeIngles;
	}

	public void setNomeIngles(String nomeIngles) {
		this.nomeIngles = nomeIngles;
	}

	public String getNomeAlemao() {
		return nomeAlemao;
	}

	public void setNomeAlemao(String nomeAlemao) {
		this.nomeAlemao = nomeAlemao;
	}

	public String getNomeEspanhol() {
		return nomeEspanhol;
	}

	public void setNomeEspanhol(String nomeEspanhol) {
		this.nomeEspanhol = nomeEspanhol;
	}

}
