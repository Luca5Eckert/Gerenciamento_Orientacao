package service.commandos;

import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoAdicionarOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoDto orientacaoDto;
	private OrientacaoService service;
	private String idOrientacao;

	public ComandoAdicionarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoDto,
			OrientacaoService service, String idOrientacao) {
		super(usuarioEfetor);
		this.orientacaoDto = orientacaoDto;
		this.service = service;
		this.idOrientacao = idOrientacao;
	}

	@Override
	public void executarComando() {
		service.criarOrientacao(orientacaoDto, idOrientacao, usuarioEfetor.pegarIdUsuario());
	}

	@Override
	public RegistroComando devolverRegistroComando() {
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoDto.idiomaOrientacao(),
				pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.ADICIONAR_ORIENTACAO;
	}

	@Override
	public RegistroComando voltarAcao() {
		service.removerOrientacao(orientacaoDto);
		return new RegistroComando(usuarioEfetor.pegarIdUsuario(), idOrientacao, orientacaoDto.idiomaOrientacao(),
				TiposComando.DESFAZER_ADICAO_ORIENTACAO);

	}

	@Override
	public boolean validarNivelDeAcesso(SessaoUsuario sessaoUsuario) {
		if (sessaoUsuario.pegarNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO) {
			return true;
		}
		return false;
	}

}
