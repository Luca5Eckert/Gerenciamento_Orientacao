package service.pesquisas;

import java.util.List;
import java.util.stream.Collectors;

import dtos.OrientacaoDto;
import infrastructure.utilitarios.FormatacaoUtil;
import service.exceptions.orientacao.OrientacaoException;

public class PesquisaOrientacaoConteudo implements PesquisaOrientacao {

	@Override
	public List<OrientacaoDto> aplicarPesquisa(List<OrientacaoDto> orientacoes, String palavraPesquisada)
			throws OrientacaoException {

		palavraPesquisada = FormatacaoUtil.removerAcento(palavraPesquisada.toLowerCase());
		palavraPesquisada = FormatacaoUtil.removerEspacos(palavraPesquisada);

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

	private List<OrientacaoDto> retornarOrientacoesPerfeitas(List<OrientacaoDto> orientacoes,
			String palavraPesquisada) {
		return orientacoes.stream()
				.filter(o -> FormatacaoUtil.removerAcento(o.conteudo().toLowerCase()).equals(palavraPesquisada))
				.collect(Collectors.toList());
	}

	private boolean verificarSeEncontrouOrientacoes(List<OrientacaoDto> orientacoes) {
		return orientacoes != null && !orientacoes.isEmpty();
	}

	private List<OrientacaoDto> retornarOrientacoesParecidas(List<OrientacaoDto> orientacoes,
			String palavraPesquisada) {
		String[] palavrasChave = FormatacaoUtil.removerAcento(palavraPesquisada.toLowerCase()).split("\\W+");

		return orientacoes.stream().filter(o -> verificarSePalavraSeAdequa(o, palavrasChave))
				.collect(Collectors.toList());
	}

	private boolean verificarSePalavraSeAdequa(OrientacaoDto orientacao, String[] palavrasChave) {
		String[] tituloPalavras = FormatacaoUtil.removerAcento(orientacao.conteudo().toLowerCase()).split("\\W+");
		int quantidadePalavraIgual = contarQuantidadeSimilaridade(palavrasChave, tituloPalavras);
		double porcentagemSimilaridade = (double) quantidadePalavraIgual / palavrasChave.length;
		return porcentagemSimilaridade >= 0.7;
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
