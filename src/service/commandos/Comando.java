package service.commandos;

import Dominio.SessaoUsuario;

public abstract class Comando {
	protected SessaoUsuario usuarioEfetor;
	
	public Comando(SessaoUsuario usuarioEfetor) {
		this.usuarioEfetor = usuarioEfetor;
	}
	
	public SessaoUsuario pegarSessao() {
		return usuarioEfetor;
	}
	
	public abstract TiposComando pegarTipo();
	
	public abstract void voltarAcao();
}
