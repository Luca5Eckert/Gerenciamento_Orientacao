package service.pesquisas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dtos.OrientacaoDto;
import service.exceptions.orientacao.OrientacaoException;
import utilitarios.Formatacao;

public class PesquisaOrientacaoTitulo implements PesquisaOrientacao {

	@Override
	public List<OrientacaoDto> aplicarPesquisa(List<OrientacaoDto> orientacoes, String palavraPesquisada)
			throws OrientacaoException {

		palavraPesquisada = Formatacao.removerAcento(palavraPesquisada.toLowerCase());
		palavraPesquisada = Formatacao.removerEspacos(palavraPesquisada);

		List<OrientacaoDto> orientacoesEncontradas = retornarOrientacoesPerfeitas(orientacoes, palavraPesquisada);

		if (verificarSeEncontrouOrientacoes(orientacoesEncontradas)) {
			return orientacoesEncontradas;
		}

		orientacoesEncontradas = retornarOrientacoesParecidas(orientacoes, palavraPesquisada);

		if (verificarSeEncontrouOrientacoes(orientacoesEncontradas)) {
			return orientacoesEncontradas;
		}

		throw new OrientacaoException();
	}

	private List<OrientacaoDto> retornarOrientacoesPerfeitas(List<OrientacaoDto> orientacoes, String palavraPesquisada) {
		return orientacoes.stream()
				.filter(o -> Formatacao.removerAcento(o.titulo().toLowerCase()).equals(palavraPesquisada))
				.collect(Collectors.toList());
	}

	private boolean verificarSeEncontrouOrientacoes(List<OrientacaoDto> orientacoes) {
		return orientacoes != null && !orientacoes.isEmpty();
	}

	private List<OrientacaoDto> retornarOrientacoesParecidas(List<OrientacaoDto> orientacoes, String palavraPesquisada) {
		String[] palavrasChave = Formatacao.removerAcento(palavraPesquisada.toLowerCase()).split("\\W+");

		return orientacoes.stream()
				.filter(o -> verificarSePalavraSeAdequa(o, palavrasChave))
				.collect(Collectors.toList());
	}

	private boolean verificarSePalavraSeAdequa(OrientacaoDto orientacao, String[] palavrasChave) {
		String[] tituloPalavras = Formatacao.removerAcento(orientacao.titulo().toLowerCase()).split("\\W+");
		int quantidadePalavraIgual = contarQuantidadeSimilaridade(palavrasChave, tituloPalavras);
		double quantidadeMinima = palavrasChave.length / 1.3;
		return quantidadePalavraIgual >= quantidadeMinima;
	}

	private int contarQuantidadeSimilaridade(String[] palavrasChave, String[] tituloPalavras) {
		int quantidade = 0;
		for (String palavraPesquisada : palavrasChave) {
			for (String palavraTitulo : tituloPalavras) {
				if (palavraPesquisada.equalsIgnoreCase(palavraTitulo)) {
					quantidade++;
				}
			}
		}
		return quantidade;
	}

}
