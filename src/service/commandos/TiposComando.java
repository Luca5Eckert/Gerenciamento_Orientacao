package service.commandos;

import Dominio.IdiomaOrientacao;

public enum TiposComando {
    ADICIONAR_ORIENTACAO("Adicionou Orientação", "Added Guidance", "Agregó Orientación", "Führung hinzugefügt"),
    REMOVER_ORIENTACAO("Removeu Orientação", "Removed Guidance", "Eliminó Orientación", "Führung entfernt"),
    EDITAR_ORIENTACAO("Editou Orientação", "Edited Guidance", "Editó Orientación", "Führung bearbeitet"),
    DESFAZER_ADICAO_ORIENTACAO("Desfez Adição de Orientação", "Undid Guidance Addition", "Deshizo la Adición de Orientación", "Hinzufügung der Führung rückgängig gemacht"),
    DESFAZER_REMOCAO_ORIENTACAO("Desfez Remoção de Orientação", "Undid Guidance Removal", "Deshizo la Eliminación de Orientación", "Entfernung der Führung rückgängig gemacht"),
    DESFAZER_EDICAO_REMOCAO("Desfez Edição/Remoção", "Undid Edit/Removal", "Deshizo Edición/Eliminación", "Bearbeitung/Entfernung rückgängig gemacht");

    String nomePortugues;
    String nomeIngles;
    String nomeEspanhol;
    String nomeAlemao;

    private TiposComando(String nomePortugues, String nomeIngles, String nomeEspanhol, String nomeAlemao) {
        this.nomePortugues = nomePortugues;
        this.nomeIngles = nomeIngles;
        this.nomeEspanhol = nomeEspanhol;
        this.nomeAlemao = nomeAlemao;
    }

    public String pegarIdioma(IdiomaOrientacao idiomaOrientacao) {
    	return switch(idiomaOrientacao) {
    	case PORTUGUES -> getNomePortugues();
    	case INGLES -> getNomeIngles();
    	case ESPANHOL -> getNomeEspanhol();
    	case ALEMAO -> getNomeAlemao();
    	default -> getNomePortugues();
    	};
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

	public String getNomeEspanhol() {
		return nomeEspanhol;
	}

	public void setNomeEspanhol(String nomeEspanhol) {
		this.nomeEspanhol = nomeEspanhol;
	}

	public String getNomeAlemao() {
		return nomeAlemao;
	}

	public void setNomeAlemao(String nomeAlemao) {
		this.nomeAlemao = nomeAlemao;
	}
    
    
}
