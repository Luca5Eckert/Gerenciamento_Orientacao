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

public class MenuAdicionarIdiomaOrientacao extends Menu {

	private final OrientacaoService orientacaoService;
	private OrientacaoDto orientacaoDto;
	private final IdiomaOrientacao idiomaOrientacao;

	public MenuAdicionarIdiomaOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoService orientacaoService,
			Menu menuAnterior, OrientacaoDto orientacaoDto, IdiomaOrientacao idiomaOrientacao) {
		super(idiomaImplementacao);
		this.orientacaoService = orientacaoService;
		this.orientacaoDto = orientacaoDto;
		this.idiomaOrientacao = idiomaOrientacao;
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		final TipoOrientacao tipoOrientacao = orientacaoDto.tipoOrientacao();
		String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
		OrientacaoDto orientacaoCriada = null;

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
		orientacaoService.criarOrientacao(orientacaoCriada, idOrientacao);
		menuHistorico.voltarMenu(MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaoCriada, idiomaImplementacao));
	}

	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}

}
