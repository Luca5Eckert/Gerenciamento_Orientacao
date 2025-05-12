package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
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
	public Menu chamarMenu(Scanner input) {
		final TipoOrientacao tipoOrientacao = orientacaoDto.tipoOrientacao();
		String idOrientacao = orientacaoService.pegarIdOrientacao(orientacaoDto);
		OrientacaoDto orientacaoCriada = null;

		try {
			orientacaoCriada = idiomaImplementacao.mostrarMenuAdicionarNovoIdiomaOrientacao(input, idiomaOrientacao,
					tipoOrientacao);
			return tratarOpcao(orientacaoCriada, idOrientacao);
		} catch (Exception e) {
			return MenuFactory.criarMenuResultado(TipoMenu.FALHA, menuAnterior,
					idiomaImplementacao.pegarMensangemAdicaoFalhada());
		}

	}

	private Menu tratarOpcao(OrientacaoDto orientacaoCriada, String idOrientacao) {
		orientacaoService.criarOrientacao(orientacaoCriada, idOrientacao);
		return menuAnterior;
	}

	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		this.idiomaImplementacao = idiomaImplementacao;
	}
	

}
