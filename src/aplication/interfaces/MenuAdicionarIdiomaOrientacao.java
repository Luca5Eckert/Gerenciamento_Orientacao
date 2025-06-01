package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import infrastructure.dao.RegistroComandoDAO;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.commandos.Comando;
import service.commandos.ComandoAdicionarIdiomaOrientacao;
import service.commandos.ExecutadorComando;

public class MenuAdicionarIdiomaOrientacao extends Menu implements Executor {

	private ExecutadorComando executadorComando;
	private OrientacaoDto orientacaoCriada;
	
	private final OrientacaoService orientacaoService;
	private OrientacaoDto orientacaoDto;
	private final IdiomaOrientacao idiomaOrientacao;
	private SessaoUsuario sessaoUsuario;

	public MenuAdicionarIdiomaOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService,
			Menu menuAnterior, OrientacaoDto orientacaoDto, IdiomaOrientacao idiomaOrientacao, SessaoUsuario sessaoUsuario) {
		super(idiomaImplementacao);
		this.orientacaoService = orientacaoService;
		this.orientacaoDto = orientacaoDto;
		this.idiomaOrientacao = idiomaOrientacao;
		this.sessaoUsuario = sessaoUsuario;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		final TipoOrientacao tipoOrientacao = orientacaoDto.tipoOrientacao();
		String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
		orientacaoCriada = null;

		try {
			orientacaoCriada = idiomaImplementacao.mostrarMenuAdicionarNovoIdiomaOrientacao(input, idiomaOrientacao,
					tipoOrientacao);
			tratarOpcao(orientacaoCriada, idOrientacao, menuHistorico);
		} catch (SairMenuException sme) {
			menuHistorico.voltarPonteiro(1);

		} catch (Exception e) {
			menuHistorico.definirProximoMenu(MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuHistorico.pegarMenuAnterior(),
					idiomaImplementacao.pegarMensangemAdicaoFalhada(), idiomaImplementacao));
		}

	}

	private void tratarOpcao(OrientacaoDto orientacaoCriada, String idOrientacao, MenuHistorico menuHistorico) {
		executar();
		menuHistorico.voltarMenu(MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaoCriada, idiomaImplementacao, sessaoUsuario));
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

	@Override
	public void executar() {
		criarExecutadorComando();
		executadorComando.aplicarComando(idiomaImplementacao);
	}

	@Override
	public Comando pegarComando() {
		return new ComandoAdicionarIdiomaOrientacao(sessaoUsuario, orientacaoCriada, orientacaoService, orientacaoService.pegarIdOrientacao(orientacaoDto), idiomaImplementacao, orientacaoDto.idiomaOrientacao());
	}

	@Override
	public void criarExecutadorComando() {
		this.executadorComando = ExecutadorComando.criarExecutadorComando(pegarComando(),
				new RegistroComandoDAO());
	}


}
