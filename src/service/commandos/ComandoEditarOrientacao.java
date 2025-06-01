package service.commandos;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoEditarOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoService service;
	private OrientacaoDto orientacaoAntiga;
	private OrientacaoDto orientacaoNova;
	private IdiomaImplementacao idiomaImplementacao;

	private String idOrientacao;

	public ComandoEditarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoService service,
			OrientacaoDto orientacaoAntiga, OrientacaoDto orientacaoNova, IdiomaImplementacao idiomaImplementacao) {
		super(usuarioEfetor);
		this.service = service;
		this.orientacaoAntiga = orientacaoAntiga;
		this.orientacaoNova = orientacaoNova;
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public void executarComando() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao,
				usuarioEfetor.pegarIdUsuario());
	}

	@Override
	public RegistroComando devolverRegistroComando() {
		idOrientacao = service.pegarIdOrientacao(orientacaoNova);
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoNova.idiomaOrientacao(),
				pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.EDITAR_ORIENTACAO;
	}

	@Override
	public RegistroComando voltarAcao() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao,
				usuarioEfetor.pegarIdUsuario());
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoNova.idiomaOrientacao(),
				TiposComando.DESFAZER_EDICAO_REMOCAO);
	}

	@Override
	public boolean validarNivelDeAcesso() {
		int idAutorOrientacao = service.pegarIdCriadorOrientacao(idOrientacao, orientacaoAntiga.idiomaOrientacao());

		if (usuarioEfetor.pegarNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO
				|| usuarioEfetor.pegarIdUsuario() == idAutorOrientacao) {
			return true;
		}
		return false;
	}

}
