package aplication.interfaces;

import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import aplication.IdiomaFactory;
import aplication.MenuFactory;
import aplication.MenuHistorico;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.implementacoes.InglesImplementacao;

public class MenuIniciarSistema extends Menu{
	
	public MenuIniciarSistema(IdiomaImplementacao idiomaImplementacao) {
		super(idiomaImplementacao);
	}

	@Override
	public void chamarMenu(Scanner input, MenuHistorico menuHistorico) {
		try {
			System.out.println("Select a language:");
			System.out.println(IdiomaOrientacao.listaIdiomasFormatado(new InglesImplementacao()));
			int opcaoIdioma = input.nextInt();
			input.nextLine();
			
			var idiomaImplementacao = IdiomaFactory.pegarIdiomaImplementacao(IdiomaOrientacao.pegarIdioma(opcaoIdioma-1));
			
			var proximoMenu = MenuFactory.criarMenu(TipoMenu.INICIO, idiomaImplementacao);
			
			menuHistorico.definirProximoMenu(proximoMenu);
			
		} catch (Exception e ) {
			idiomaImplementacao.pegarMensagemEntradaInvalida();
		}
		
	}
	
}
