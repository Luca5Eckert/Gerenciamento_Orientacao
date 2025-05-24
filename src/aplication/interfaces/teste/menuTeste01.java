package aplication.interfaces.teste;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;

public class menuTeste01 {

	public static void main(String[] args) {
		
		var menuService = new OrientacaoService();
		
		OrientacaoDto orientacaoDto = new OrientacaoDto("lsa", TipoOrientacao.MANUAL_OPERACAO, "sllsa", IdiomaOrientacao.PORTUGUES);
		
		String id = menuService.pegarIdOrientacao(orientacaoDto);
		
		System.out.println(id);
		
		var lista = menuService.pegarOrientacoesIdiomas(id);
		
		System.out.println(lista);
		
		menuService.removerOrientacao(orientacaoDto);
	}
	
	
}
