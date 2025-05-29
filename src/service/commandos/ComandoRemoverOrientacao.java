package service.commandos;

import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoRemoverOrientacao extends Comando {

	private OrientacaoService service;
	private OrientacaoDto orientacaoRemover;

	public ComandoRemoverOrientacao(SessaoUsuario usuarioEfetor, OrientacaoService service,
			OrientacaoDto orientacaoRemover) {
		super(usuarioEfetor);
		this.service = service;
		this.orientacaoRemover = orientacaoRemover;
	}

	@Override
	public RegistroComando executarComando() {
		var registroComando = devolverRegistroComando();
		service.removerOrientacao(orientacaoRemover);
		return registroComando;
	}

	@Override
	public RegistroComando devolverRegistroComando() {
		String idOrientacao = service.pegarIdOrientacao(orientacaoRemover);
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.REMOVER_ORIENTACAO;
	}

	@Override
	public void voltarAcao() {
		service.criarOrientacao(orientacaoRemover);
	}

}
