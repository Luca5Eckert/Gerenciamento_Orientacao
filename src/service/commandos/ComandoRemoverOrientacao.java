package service.commandos;

import service.SessaoUsuario;

public class ComandoRemoverOrientacao extends Comando{
	

	public ComandoRemoverOrientacao(SessaoUsuario usuarioEfetor) {
		super(usuarioEfetor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.REMOVER_ORIENTACAO;
	}

	@Override
	public void voltarAcao() {
		// TODO Auto-generated method stub
		
	}

}
