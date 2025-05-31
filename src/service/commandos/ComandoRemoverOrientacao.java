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
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoRemover.idiomaOrientacao(),
				pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.REMOVER_ORIENTACAO;
	}

	@Override
	public RegistroComando voltarAcao() {
		service.desfazerRemocaoOrientacao(idOrientacao, orientacaoRemover.idiomaOrientacao());
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoRemover.idiomaOrientacao(),
				TiposComando.DESFAZER_REMOCAO_ORIENTACAO);
	}

	@Override
	public boolean validarNivelDeAcesso(SessaoUsuario sessaoUsuario) {
		int idAutorOrientacao = service.pegarIdCriadorOrientacao(idOrientacao, orientacaoRemover.idiomaOrientacao());

		if (sessaoUsuario.pegarNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO
				|| usuarioEfetor.pegarIdUsuario() == idAutorOrientacao) {
			return true;
		}
		return false;
	}

}
