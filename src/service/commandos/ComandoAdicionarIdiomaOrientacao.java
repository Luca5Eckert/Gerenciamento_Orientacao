package service.commandos;

import Dominio.IdiomaOrientacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;

public class ComandoAdicionarIdiomaOrientacao extends Comando {
	private final int NIVEL_DE_ACESSO_MINIMO = 2;

	private OrientacaoDto orientacaoDto;
	private OrientacaoService service;
	private String idOrientacao;
	
	private IdiomaOrientacao idiomaAntigo;

	public ComandoAdicionarIdiomaOrientacao(SessaoUsuario usuarioEfetor, OrientacaoDto orientacaoDto,
			OrientacaoService service, String idOrientacao, IdiomaOrientacao idiomaAntigo) {
		super(usuarioEfetor);
		this.orientacaoDto = orientacaoDto;
		this.service = service;
		this.idOrientacao = idOrientacao;
		this.idiomaAntigo = idiomaAntigo;
	}

	@Override
	public void executarComando() {
		if (verificarSeOrientacaoExiste()) {
			service.atualizarOrientacao(orientacaoDto, idOrientacao, usuarioEfetor.pegarIdUsuario());
			return;
		}
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
	public boolean validarNivelDeAcesso() {
		int idAutorOrientacao = service.pegarIdCriadorOrientacao(idOrientacao, idiomaAntigo);

		if (usuarioEfetor.pegarNivelAcesso() > NIVEL_DE_ACESSO_MINIMO
				|| usuarioEfetor.pegarIdUsuario() == idAutorOrientacao) {
			return true;
		}
		return false;
	}

	public boolean verificarSeOrientacaoExiste() {
		return service.verificarOrientacaoExiste(orientacaoDto.idiomaOrientacao(), idOrientacao);
	}

}
