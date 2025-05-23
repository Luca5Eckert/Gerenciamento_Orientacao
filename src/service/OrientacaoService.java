package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Dominio.IdiomaOrientacao;
import Dominio.Orientacao;
import Dominio.OrientacaoId;
import Dominio.TipoOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.OrientacaoNaoDisponivelIdiomaException;
import dtos.OrientacaoDto;
import repositorio.OrientacaoRepositorio;
import service.exceptions.orientacao.OrientacaoException;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.pesquisas.PesquisaFactory;
import service.pesquisas.PesquisaOrientacao;
import service.pesquisas.PesquisaTipo;

public class OrientacaoService {
	private OrientacaoRepositorio repositorioOrientacao;
	private PesquisaOrientacao pesquisaOrientacao;

	public OrientacaoService() {
		this.repositorioOrientacao = new OrientacaoRepositorio();
	}

	public void criarOrientacao(OrientacaoDto orientacaoDto) {
		Orientacao orientacaoModelo = transformarDtoModelo(orientacaoDto);
		repositorioOrientacao.adicionarOrientacao(orientacaoModelo);
	}

	public void criarOrientacao(OrientacaoDto orientacaoDto, String idOrientacao) {
		Orientacao orientacaoModelo = transformarDtoModelo(orientacaoDto, idOrientacao);
		repositorioOrientacao.adicionarOrientacao(orientacaoModelo);
	}

	public void removerOrientacao(OrientacaoDto orientacaoDto) {
		String stringIdOrientacao = pegarIdOrientacao(orientacaoDto);

		var orientacaoId = new OrientacaoId(stringIdOrientacao, orientacaoDto.idiomaOrientacao());

		repositorioOrientacao.removerOrientacao(orientacaoId);
	}

	public boolean criarOrientacoes(List<OrientacaoDto> listaOrientacao) {

		String idOrientacao = UUID.randomUUID().toString();

		for (OrientacaoDto orientacaoDto : listaOrientacao) {
			Orientacao orientacaoModelo = transformarDtoModelo(orientacaoDto, idOrientacao);
			repositorioOrientacao.adicionarOrientacao(orientacaoModelo);
		}
		return true;
	}

	public List<OrientacaoDto> pesquisarOrientacao(String palavra, List<OrientacaoDto> listaOrientacao)
			throws OrientacaoException {
		this.pesquisaOrientacao = PesquisaFactory.pegarPesquisa(PesquisaTipo.TITULO);
		listaOrientacao = pesquisaOrientacao.aplicarPesquisa(listaOrientacao, palavra);
		return listaOrientacao;
	}

	public Map<IdiomaOrientacao, OrientacaoDto> pegarOrientacoesIdiomas(String idOrientacoes) {
		var listaOrientacoesIdiomas = new HashMap<IdiomaOrientacao, OrientacaoDto>();
		for (IdiomaOrientacao idioma : IdiomaOrientacao.values()) {
			Orientacao orientacao = repositorioOrientacao.pegarOrientacaoPorIdeIdioma(idOrientacoes, idioma.name());

			if (orientacao != null) {
				var orientacaoDto = transformarModeloDto(orientacao);
				listaOrientacoesIdiomas.put(idioma, orientacaoDto);
			}

		}

		return listaOrientacoesIdiomas;
	}

	public List<OrientacaoDto> aplicarFiltro(GerenciadorFiltrosOrientacao gerenciadorFiltro)
			throws OrientacaoException {
		var listaOriginal = pegarTodasOrientacoes();
		return gerenciadorFiltro.aplicarFiltros(listaOriginal, this);
	}

	public boolean atualizarOrientacao(OrientacaoDto orientacaoAlterada, OrientacaoDto orientacaoAntiga,
			IdiomaImplementacao idiomaImplementacao) throws OrientacaoNaoDisponivelIdiomaException {
		String idOrientacao = pegarIdOrientacao(orientacaoAntiga);

		if (!orientacaoAlterada.idiomaOrientacao().equals(orientacaoAntiga.idiomaOrientacao())) {

			boolean idiomaDisponivel = repositorioOrientacao.verificarIdiomaOrientacao(idOrientacao,
					orientacaoAlterada.idiomaOrientacao());

			if (!idiomaDisponivel) {
				throw new OrientacaoNaoDisponivelIdiomaException(
						idiomaImplementacao.pegarMensagemIdiomaNaoDisponivel());
			}
			
			repositorioOrientacao.atualizarIdioma(idOrientacao, orientacaoAntiga.idiomaOrientacao(), orientacaoAlterada.idiomaOrientacao());
		}

		var orientacaoModelo = transformarDtoModelo(orientacaoAlterada, idOrientacao);

		if (orientacaoModelo.getTipoOrientacao().equals(orientacaoAntiga.tipoOrientacao())) {
			repositorioOrientacao.atualizarTiposOrientacoes(idOrientacao, orientacaoModelo.getTipoOrientacao());
		}

		return this.repositorioOrientacao.atualizarOrientacao(orientacaoModelo);
	}

	public List<OrientacaoDto> pegarTodasOrientacoes() {
		var listaModelo = this.repositorioOrientacao.getOrientacaoRepositorio();
		return transformarListaModeloDto(listaModelo);
	}

	public OrientacaoRepositorio getRepositorioOrientacao() {
		return repositorioOrientacao;
	}

	public void setRepositorioOrientacao(OrientacaoRepositorio repositorioOrientacao) {
		this.repositorioOrientacao = repositorioOrientacao;
	}

	public Orientacao transformarDtoModelo(OrientacaoDto orientacaoDto) {
		Orientacao orientacaoModelo = new Orientacao();

		orientacaoModelo.setIdOrientacao(new OrientacaoId(orientacaoDto.idiomaOrientacao()));
		orientacaoModelo.setTitulo(orientacaoDto.titulo());
		orientacaoModelo.setTipoOrientacao(orientacaoDto.tipoOrientacao());
		orientacaoModelo.setConteudo(orientacaoDto.conteudo());

		return orientacaoModelo;
	}

	public Orientacao transformarDtoModelo(OrientacaoDto orientacaoDto, String idOrientacao) {
		Orientacao orientacaoModelo = new Orientacao();

		OrientacaoId orientacaoId = new OrientacaoId(idOrientacao, orientacaoDto.idiomaOrientacao());
		orientacaoModelo.setIdOrientacao(orientacaoId);
		orientacaoModelo.setTitulo(orientacaoDto.titulo());
		orientacaoModelo.setTipoOrientacao(orientacaoDto.tipoOrientacao());
		orientacaoModelo.setConteudo(orientacaoDto.conteudo());

		return orientacaoModelo;
	}

	public OrientacaoDto transformarModeloDto(Orientacao orientacao) {
		return new OrientacaoDto(orientacao.getTitulo(), orientacao.getTipoOrientacao(), orientacao.getConteudo(),
				orientacao.getIdOrientacao().getIdiomaOrientacao());
	}

	public List<OrientacaoDto> transformarListaModeloDto(List<Orientacao> listaOrientacao) {
		var orientacaoRepositorioDto = new ArrayList<OrientacaoDto>();
		for (Orientacao orientacao : listaOrientacao) {
			orientacaoRepositorioDto.add(transformarModeloDto(orientacao));
		}
		return orientacaoRepositorioDto;
	}

	public String pegarIdOrientacao(OrientacaoDto orientacaoDto) {
		var orientacaoModelo = transformarDtoModelo(orientacaoDto);
		return repositorioOrientacao.pegarIndexOrientacao(orientacaoModelo);
	}
}
