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
	
	public ComandoEditarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoAntiga, OrientacaoDto orientacaoNova, IdiomaImplementacao idiomaImplementacao) {
		super(usuarioEfetor);
		this.orientacaoAntiga = orientacaoAntiga;
		this.orientacaoNova = orientacaoNova;
		this.idiomaImplementacao = idiomaImplementacao;
	}
	

	@Override
	public void executarComando() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao);
	}
	
	@Override
	public RegistroComando devolverRegistroComando() {
		String idOrientacao = service.pegarIdOrientacao(orientacaoAntiga);
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoNova.idiomaOrientacao(), pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.EDITAR_ORIENTACAO;
	}

	@Override
	public void voltarAcao() {
		service.atualizarOrientacao(orientacaoNova, orientacaoAntiga, idiomaImplementacao);
	}

	@Override
	public boolean validarNivelDeAcesso(SessaoUsuario sessaoUsuario) {
		if (sessaoUsuario.pegarNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO) {
			return true;
		}
		return false;
	}

}
