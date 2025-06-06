package service.commandos;

import Dominio.IdiomaOrientacao;
import Dominio.NivelAcesso;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class ComandoAdicionarIdiomaOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoDto orientacaoDto;
	private OrientacaoService service;
	private String idOrientacao;
	
	private IdiomaOrientacao idiomaAntigo;

	public ComandoAdicionarIdiomaOrientacao(int idUsuario, OrientacaoDto orientacaoDto,
			OrientacaoService service, String idOrientacao, IdiomaOrientacao idiomaAntigo) {
		super(idUsuario);
		this.orientacaoDto = orientacaoDto;
		this.service = service;
		this.idOrientacao = idOrientacao;
		this.idiomaAntigo = idiomaAntigo;
	}

	@Override
	public void executarComando() {
		if (verificarSeOrientacaoExiste()) {
			service.atualizarOrientacao(orientacaoDto, idOrientacao, idUsuario);
			return;
		}
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
	public boolean validarNivelDeAcesso(NivelAcesso nivelAcesso) {
		idOrientacao = service.pegarIdOrientacao(orientacaoDto);
		int idAutorOrientacao = service.pegarIdCriadorOrientacao(idOrientacao, idiomaAntigo);

		if (nivelAcesso.getNivelAcesso() > NIVEL_DE_ACESSO_MINIMO
				|| idUsuario == idAutorOrientacao) {
			return true;
		}
		return false;
	}

	public boolean verificarSeOrientacaoExiste() {
		return service.verificarOrientacaoExiste(orientacaoDto.idiomaOrientacao(), idOrientacao);
	}

}
