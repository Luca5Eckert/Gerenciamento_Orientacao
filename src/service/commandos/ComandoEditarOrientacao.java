package service.commandos;

import dtos.OrientacaoDto;
import service.SessaoUsuario;

public class ComandoEditarOrientacao extends Comando {
	
	private String idOrientacao;
	private OrientacaoDto orientacaoAntiga;
	private OrientacaoDto orientacaoNova;
	
	public ComandoEditarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoAntiga, OrientacaoDto orientacaoNova) {
		super(usuarioEfetor);
		this.orientacaoAntiga = orientacaoAntiga;
		this.orientacaoNova = orientacaoNova;
	}

	@Override
	public TiposComando pegarTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void voltarAcao() {
		// TODO Auto-generated method stub
		
	}

}
