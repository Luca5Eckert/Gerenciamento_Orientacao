package service.commandos;

import Dominio.NivelAcesso;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class ComandoEditarOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoService service;
	private OrientacaoDto orientacaoAntiga;
	private OrientacaoDto orientacaoNova;
	private IdiomaImplementacao idiomaImplementacao;

	private String idOrientacao;

	public ComandoEditarOrientacao(int idUsuario, OrientacaoService service, OrientacaoDto orientacaoAntiga,
			OrientacaoDto orientacaoNova, IdiomaImplementacao idiomaImplementacao) {
		super(idUsuario);
		this.service = service;
		this.orientacaoAntiga = orientacaoAntiga;
		this.orientacaoNova = orientacaoNova;
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public void executarComando() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao, idUsuario);
	}

	@Override
	public RegistroComando devolverRegistroComando() {
		idOrientacao = service.pegarIdOrientacao(orientacaoNova);
		return new RegistroComando(idUsuario, idOrientacao, orientacaoNova.idiomaOrientacao(), pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.EDITAR_ORIENTACAO;
	}

	@Override
	public RegistroComando voltarAcao() {
		service.atualizarOrientacao(orientacaoAntiga, orientacaoNova, idiomaImplementacao, idUsuario);
		return new RegistroComando(idUsuario, idOrientacao, orientacaoNova.idiomaOrientacao(),
				TiposComando.DESFAZER_EDICAO_REMOCAO);
	}

	@Override
	public boolean validarNivelDeAcesso(NivelAcesso nivelAcesso) {
		if(idOrientacao==null) {
			idOrientacao = service.pegarIdOrientacao(orientacaoAntiga);
		}
		int idAutorOrientacao = service.pegarIdCriadorOrientacao(idOrientacao, orientacaoAntiga.idiomaOrientacao());

		if (nivelAcesso.getNivelAcesso() > NIVEL_DE_ACESSO_MINIMO || idUsuario == idAutorOrientacao) {
			return true;
		}
		return false;
	}

}
