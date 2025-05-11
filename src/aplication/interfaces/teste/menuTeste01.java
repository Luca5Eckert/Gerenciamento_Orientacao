package aplication.interfaces.teste;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.implementacoes.PortuguesImplementacao;
import aplication.interfaces.*;
import dtos.OrientacaoDto;
import repositorio.UsuarioRepositorio;
import service.OrientacaoService;


public class menuTeste01 {

	public static void main(String[] args) {
		
		var menuService = new OrientacaoService();
		
		OrientacaoDto orientacaoDto = new OrientacaoDto("al", TipoOrientacao.MANUTENCAO_REPAROS, "ala", IdiomaOrientacao.ALEMAO);
		
		String id = menuService.pegarIdOrientacao(orientacaoDto);
		
		System.out.println(id);
		
		var lista = menuService.pegarOrientacoesIdiomas(id);
		
		System.out.println(lista);
	}
	
	
}
