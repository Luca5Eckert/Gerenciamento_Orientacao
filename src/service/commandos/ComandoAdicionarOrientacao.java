package service.commandos;

import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoAdicionarOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoDto orientacaoDto;
	private OrientacaoService service;

	public ComandoAdicionarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoDto,
			OrientacaoService service) {
		super(usuarioEfetor);
		this.orientacaoDto = orientacaoDto;
		this.service = service;
	}

	@Override
	public void executarComando() {
		service.criarOrientacao(orientacaoDto);
	}

	@Override
	public RegistroComando devolverRegistroComando() {
		String idOrientacao = service.pegarIdOrientacao(orientacaoDto);

		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.ADICIONAR_ORIENTACAO;
	}

	@Override
	public void voltarAcao() {
		service.removerOrientacao(orientacaoDto);
	}

	@Override
	public boolean validarNivelDeAcesso(SessaoUsuario sessaoUsuario) {
		if (sessaoUsuario.pegarNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO) {
			return true;
		}
		return false;
	}

}
