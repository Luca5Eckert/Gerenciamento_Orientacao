package service.commandos;

import Dominio.NivelAcesso;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class ComandoRemoverOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoService service;
	private OrientacaoDto orientacaoRemover;

	private String idOrientacao;

	public ComandoRemoverOrientacao(int idUsuario, OrientacaoService service, OrientacaoDto orientacaoRemover) {
		super(idUsuario);
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
		return new RegistroComando(idUsuario, idOrientacao, orientacaoRemover.idiomaOrientacao(), pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.REMOVER_ORIENTACAO;
	}

	@Override
	public RegistroComando voltarAcao() {
		service.desfazerRemocaoOrientacao(idOrientacao, orientacaoRemover.idiomaOrientacao());
		return new RegistroComando(idUsuario, idOrientacao, orientacaoRemover.idiomaOrientacao(),
				TiposComando.DESFAZER_REMOCAO_ORIENTACAO);
	}

	@Override
	public boolean validarNivelDeAcesso(NivelAcesso nivelAcesso) {
		idOrientacao = service.pegarIdOrientacao(orientacaoRemover);
		int idAutorOrientacao = service.pegarIdCriadorOrientacao(idOrientacao, orientacaoRemover.idiomaOrientacao());

		if (nivelAcesso.getNivelAcesso() > NIVEL_DE_ACESSO_MINIMO || idUsuario == idAutorOrientacao) {
			return true;
		}
		return false;
	}

}
