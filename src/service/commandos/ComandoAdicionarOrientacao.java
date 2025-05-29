package service.commandos;

import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoAdicionarOrientacao extends Comando {
	
	private OrientacaoDto orientacaoDto;
	private OrientacaoService service;

	public ComandoAdicionarOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoDto,
			OrientacaoService service) {
		super(usuarioEfetor);
		this.orientacaoDto = orientacaoDto;
		this.service = service;
	}

	@Override
	public RegistroComando executarComando() {
		service.criarOrientacao(orientacaoDto);
		return devolverRegistroComando();
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

}
