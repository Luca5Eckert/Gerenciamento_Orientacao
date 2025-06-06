package service.commandos;

import Dominio.NivelAcesso;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class ComandoAdicionarOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoDto orientacaoDto;
	private OrientacaoService service;
	private String idOrientacao;

	public ComandoAdicionarOrientacao(int idUsuario, OrientacaoDto orientacaoDto,
			OrientacaoService service, String idOrientacao) {
		super(idUsuario);
		this.orientacaoDto = orientacaoDto;
		this.service = service;
		this.idOrientacao = idOrientacao;
	}

	@Override
	public void executarComando() {
		service.criarOrientacao(orientacaoDto, idOrientacao, idUsuario);
	}

	@Override
	public RegistroComando devolverRegistroComando() {
		return new RegistroComando(idUsuario, idOrientacao, orientacaoDto.idiomaOrientacao(),
				pegarTipo());
	}

	@Override
	public TiposComando pegarTipo() {
		return TiposComando.ADICIONAR_ORIENTACAO;
	}

	@Override
	public RegistroComando voltarAcao() {
		service.removerOrientacao(orientacaoDto);
		return new RegistroComando(idUsuario, idOrientacao, orientacaoDto.idiomaOrientacao(),
				TiposComando.DESFAZER_ADICAO_ORIENTACAO);

	}
	
	@Override
	public RegistroComando refazerAcao() {
		service.atualizarOrientacao(orientacaoDto, idOrientacao, idUsuario);
		return devolverRegistroComando();
	}
	

	@Override
	public boolean validarNivelDeAcesso(NivelAcesso nivelAcesso) {
		if (nivelAcesso.getNivelAcesso() >= NIVEL_DE_ACESSO_MINIMO) {
			return true;
		}
		return false;
	}

}
