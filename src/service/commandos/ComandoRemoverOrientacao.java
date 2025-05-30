package service.commandos;

import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoRemoverOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;
	
	private OrientacaoService service;
	private OrientacaoDto orientacaoRemover;
	
	private String idOrientacao;

	public ComandoRemoverOrientacao(SessaoUsuario usuarioEfetor, OrientacaoService service,
			OrientacaoDto orientacaoRemover) {
		super(usuarioEfetor);
		this.service = service;
		this.orientacaoRemover = orientacaoRemover;
	}

	@Override
	public void executarComando() {
		salvarIdOrientacao();
		service.removerOrientacao(orientacaoRemover);
	}

	private void salvarIdOrientacao() {
		idOrientacao = service.pegarIdOrientacao(orientacaoRemover);
	}

	@Override
	public RegistroComando devolverRegistroComando() {
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

	@Override
	public boolean validarNivelDeAcesso(SessaoUsuario sessaoUsuario) {
		if (sessaoUsuario.pegarNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO) {
			return true;
		}
		return false;
	}

}
