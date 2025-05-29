package service.commandos;

import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoEditarOrientacao extends Comando {
	
	private OrientacaoService service;
	private OrientacaoDto orientacaoAntiga;
	private OrientacaoDto orientacaoNova;
	private IdiomaImplementacao idiomaImplementacao;
	
	public ComandoEditarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoAntiga, OrientacaoDto orientacaoNova, IdiomaImplementacao idiomaImplementacao) {
		super(usuarioEfetor);
		this.orientacaoAntiga = orientacaoAntiga;
		this.orientacaoNova = orientacaoNova;
		this.idiomaImplementacao = idiomaImplementacao;
	}
	
	@Override
	public RegistroComando executarComando() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao);
		return devolverRegistroComando();
	}
	
	@Override
	public RegistroComando devolverRegistroComando() {
		String idOrientacao = service.pegarIdOrientacao(orientacaoNova);
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.EDITAR_ORIENTACAO;
	}

	@Override
	public void voltarAcao() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao);
	}

}
