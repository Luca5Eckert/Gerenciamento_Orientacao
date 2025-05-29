package service.commandos;

import service.SessaoUsuario;

public abstract class Comando {
	protected SessaoUsuario usuarioEfetor;
	
	public Comando(SessaoUsuario usuarioEfetor) {
		this.usuarioEfetor = usuarioEfetor;
	}
	
	public SessaoUsuario pegarSessao() {
		return usuarioEfetor;
	}
	
	public abstract RegistroComando executarComando();
	
	public abstract RegistroComando devolverRegistroComando();
	
	public abstract TiposComando pegarTipo();
	
	public abstract void voltarAcao();
}
