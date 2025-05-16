package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.SairMenuException;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class MenuAdicionarIdiomaOrientacao implements Menu {

	private IdiomaImplementacao idiomaImplementacao;
	private final OrientacaoService orientacaoService;
	private Menu menuAnterior;
	private OrientacaoDto orientacaoDto;
	private final IdiomaOrientacao idiomaOrientacao;

	public MenuAdicionarIdiomaOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService,
			Menu menuAnterior, OrientacaoDto orientacaoDto, IdiomaOrientacao idiomaOrientacao) {
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaoService = orientacaoService;
		this.menuAnterior = menuAnterior;
		this.orientacaoDto = orientacaoDto;
		this.idiomaOrientacao = idiomaOrientacao;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		Menu proximoMenu = null;
		final TipoOrientacao tipoOrientacao = orientacaoDto.tipoOrientacao();
		String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
		OrientacaoDto orientacaoCriada = null;

		try {
			orientacaoCriada = idiomaImplementacao.mostrarMenuAdicionarNovoIdiomaOrientacao(input, idiomaOrientacao,
					tipoOrientacao);
			proximoMenu = tratarOpcao(orientacaoCriada, idOrientacao);
		} catch (SairMenuException sme) {
			proximoMenu = menuAnterior;

		} catch (Exception e) {
			proximoMenu = MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuAnterior,
					idiomaImplementacao.pegarMensangemAdicaoFalhada(), idiomaImplementacao);
		} finally {
			menuHistorico.definirProximoMenu(proximoMenu);
		}

	}

	private Menu tratarOpcao(OrientacaoDto orientacaoCriada, String idOrientacao) {
		if (orientacaoCriada == null) {
			return this;
		}
		orientacaoService.criarOrientacao(orientacaoCriada, idOrientacao);
		return menuAnterior;
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
