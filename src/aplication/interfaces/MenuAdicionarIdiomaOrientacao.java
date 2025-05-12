package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.MenuFactory;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;

public class MenuAdicionarIdiomaOrientacao implements Menu {
	
	private IdiomaImplementacao idiomaImplementacao;
	private MenuAnterior menuAnterior;
	private final OrientacaoDto orientacaDto;
	private final String idOrientacao;
	private final IdiomaOrientacao idiomaOrientacao;
	
	public MenuAdicionarIdiomaOrientacao(IdiomaImplementacao idiomaImplementacao, OrientacaoDto orientacaDto,
			String idOrientacao, IdiomaOrientacao idiomaOrientacao) {
		super();
		this.idiomaImplementacao = idiomaImplementacao;
		this.orientacaDto = orientacaDto;
		this.idOrientacao = idOrientacao;
		this.idiomaOrientacao = idiomaOrientacao;
	}

	@Override
	public Menu chamarMenu(Scanner input) {
		var tipoOrientacao = orientacaDto.tipoOrientacao();
		try {
			idiomaImplementacao.mostrarMenuAdicionarNovoIdiomaOrientacao(input, idiomaOrientacao, tipoOrientacao);
			
		} catch (Exception e ) {
			return MenuFactory.criarMenuPesquisa(TipoMenu.MOSTRAR_ORIENTACAO, orientacaDto, menuAnterior);
		}
		
		return tratarOpcao(idOrientacao);
	}
	
	public Menu tratarOpcao(String opcao){
		return switch(opcao) {
		case 
		}
	}

	@Override
	public void mudarIdioma(IdiomaImplementacao idiomaImplementacao) {
		// TODO Auto-generated method stub
		
	}

}
