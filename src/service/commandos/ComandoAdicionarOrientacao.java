package service.commandos;

import repositorio.OrientacaoRepositorio;
import service.SessaoUsuario;

public class ComandoAdicionarOrientacao extends Comando {
	private String idOrientacao;
	private OrientacaoRepositorio repositorio;

	public ComandoAdicionarOrientacao(SessaoUsuario usuarioEfetor, String idOrientacao, OrientacaoRepositorio repositorio) {
		super(usuarioEfetor);
		this.idOrientacao = idOrientacao;
		this.repositorio = repositorio;
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.ADICIONAR_ORIENTACAO;
	}

	@Override
	public void voltarAcao() {
		repositorio.removerOrientacaoPorId(idOrientacao);
	}

}
