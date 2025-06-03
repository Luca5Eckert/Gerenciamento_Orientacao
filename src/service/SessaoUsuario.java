package service;

import Dominio.NivelAcesso;

public class SessaoUsuario {

	private final int idUsuario;
	private final NivelAcesso nivelDeAcesso;
	private final ComandoHistorico comandoHistorico;

	public SessaoUsuario(int idUsuario, NivelAcesso nivelDeAcesso, ComandoHistorico comandoHistorico) {
		this.idUsuario = idUsuario;
		this.nivelDeAcesso = nivelDeAcesso;
		this.comandoHistorico = comandoHistorico;
	}

	public int pegarIdUsuario() {
		return idUsuario;
	}
	
	public int pegarNivelAcesso() {
		return nivelDeAcesso.getNivelAcesso();
	}

	public void salvarComando(Comando comando){
		this.comandoHistorico.adicionarComando(comando);
	}

	public ComandoHistorico pegarHistoricoComandos(){
		return this.comandoHistorico;
	}
	

}
