package service.pesquisas.testes;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import service.exceptions.orientacao.OrientacaoException;
import service.pesquisas.PesquisaOrientacaoTitulo;

public class PesquisaOrientacaoTeste01 {

	public static void main(String[] args) {
		OrientacaoDto o1 = new OrientacaoDto("Manual de Operação Básico", TipoOrientacao.MANUAL_OPERACAO, "Conteúdo 1",
				IdiomaOrientacao.PORTUGUES);
		OrientacaoDto o2 = new OrientacaoDto("Procedimento testes de Básico Industrial",
				TipoOrientacao.PROCEDIMENTO_SEGURANCA, "Conteúdo 2", IdiomaOrientacao.PORTUGUES);
		OrientacaoDto o3 = new OrientacaoDto("Guia Manual de Testes e Diagnóstico Básico",
				TipoOrientacao.TESTES_DIAGNOSTICO, "Conteúdo 3", IdiomaOrientacao.PORTUGUES);

		List<OrientacaoDto> orientacoes = Arrays.asList(o1, o2, o3);

		PesquisaOrientacaoTitulo pesquisa = new PesquisaOrientacaoTitulo();
		Scanner sc = new Scanner(System.in);
		String termoBusca = sc.nextLine();

		try {
			List<OrientacaoDto> resultados = pesquisa.aplicarPesquisa(orientacoes, termoBusca);
			System.out.println("Orientações encontradas:");
			for (OrientacaoDto resultado : resultados) {
				System.out.println(" - " + resultado.titulo());
			}
		} catch (OrientacaoException e) {
			System.out.println("Nenhuma orientação encontrada para o termo: " + termoBusca.replaceAll("\\s+", " "));
		}
	}
	
}
