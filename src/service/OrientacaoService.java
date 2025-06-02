package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Dominio.IdiomaOrientacao;
import Dominio.Orientacao;
import Dominio.OrientacaoId;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.exceptions.OrientacaoNaoDisponivelIdiomaException;
import dtos.OrientacaoDto;
import repositorio.OrientacaoRepositorio;
import service.exceptions.orientacao.OrientacaoException;
import service.filtros.GerenciadorFiltrosOrientacao;
import service.pesquisas.PesquisaFactory;

public class OrientacaoService {
	private OrientacaoRepositorio repositorioOrientacao;

	public OrientacaoService() {
		this.repositorioOrientacao = new OrientacaoRepositorio();
	}

	public void criarOrientacao(OrientacaoDto orientacaoDto) {
		Orientacao orientacaoModelo = transformarDtoModelo(orientacaoDto);
		repositorioOrientacao.adicionarOrientacao(orientacaoModelo);
	}

	public void criarOrientacao(OrientacaoDto orientacaoDto, String idOrientacao, int idUsuario) {
		Orientacao orientacaoModelo = transformarDtoModelo(orientacaoDto, idOrientacao, idUsuario);
		repositorioOrientacao.adicionarOrientacao(orientacaoModelo);
	}

	public void desfazerRemocaoOrientacao(String idOrientacao, IdiomaOrientacao idiomaOrientacao) {
		repositorioOrientacao.desfazerRemocaoOrientacao(idOrientacao, idiomaOrientacao);
	}

	public void removerOrientacao(OrientacaoDto orientacaoDto) {
		String stringIdOrientacao = pegarIdOrientacao(orientacaoDto);

		var orientacaoId = new OrientacaoId(stringIdOrientacao, orientacaoDto.idiomaOrientacao());

		repositorioOrientacao.removerOrientacao(orientacaoId);
	}

	public boolean criarOrientacoes(List<OrientacaoDto> listaOrientacao, int idUsuario) {

		String idOrientacao = UUID.randomUUID().toString();

		for (OrientacaoDto orientacaoDto : listaOrientacao) {
			Orientacao orientacaoModelo = transformarDtoModelo(orientacaoDto, idOrientacao, idUsuario);
			repositorioOrientacao.adicionarOrientacao(orientacaoModelo);
		}
		return true;
	}

	public List<OrientacaoDto> pesquisarOrientacao(String palavra, List<OrientacaoDto> listaOrientacao)
			throws OrientacaoException {
		List<OrientacaoDto> listaPesquisada = new ArrayList<OrientacaoDto>();

		try {
			listaPesquisada = PesquisaFactory.toTitulo().aplicarPesquisa(listaOrientacao, palavra);

		} catch (OrientacaoException oe) {
			listaPesquisada = PesquisaFactory.toConteudo().aplicarPesquisa(listaOrientacao, palavra);

		}

		return listaPesquisada;
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
			IdiomaImplementacao idiomaImplementacao, int idUsuario) throws OrientacaoNaoDisponivelIdiomaException {
		String idOrientacao = pegarIdOrientacao(orientacaoAntiga);

		if (!orientacaoAlterada.idiomaOrientacao().equals(orientacaoAntiga.idiomaOrientacao())) {

			boolean idiomaDisponivel = repositorioOrientacao.verificarIdiomaOrientacao(idOrientacao,
					orientacaoAlterada.idiomaOrientacao());

			if (!idiomaDisponivel) {
				throw new OrientacaoNaoDisponivelIdiomaException(
						idiomaImplementacao.pegarMensagemIdiomaNaoDisponivel());
			}

			repositorioOrientacao.atualizarIdioma(idOrientacao, orientacaoAntiga.idiomaOrientacao(),
					orientacaoAlterada.idiomaOrientacao());
		}

		var orientacaoModelo = transformarDtoModelo(orientacaoAlterada, idOrientacao, idUsuario);

		if (orientacaoModelo.getTipoOrientacao().name().equals(orientacaoAntiga.tipoOrientacao().name())) {
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

	public void atualizarOrientacao(OrientacaoDto orientacaoAlterada, String idOrientacao, int idUsuario) {
 		var orientacaoId = new OrientacaoId(idOrientacao, orientacaoAlterada.idiomaOrientacao());
		var orientacaoModelo = transformarDtoModelo(orientacaoAlterada, idOrientacao, idUsuario);
		repositorioOrientacao.atualizarOrientacao(orientacaoId, orientacaoModelo);
	}

	public Orientacao transformarDtoModelo(OrientacaoDto orientacaoDto) {
		Orientacao orientacaoModelo = new Orientacao();

		orientacaoModelo.setIdOrientacao(new OrientacaoId(orientacaoDto.idiomaOrientacao()));
		orientacaoModelo.setTitulo(orientacaoDto.titulo());
		orientacaoModelo.setTipoOrientacao(orientacaoDto.tipoOrientacao());
		orientacaoModelo.setConteudo(orientacaoDto.conteudo());

		return orientacaoModelo;
	}

	public Orientacao transformarDtoModelo(OrientacaoDto orientacaoDto, String idOrientacao, int usuarioCriador) {
		Orientacao orientacaoModelo = new Orientacao();

		OrientacaoId orientacaoId = new OrientacaoId(idOrientacao, orientacaoDto.idiomaOrientacao());
		orientacaoModelo.setIdOrientacao(orientacaoId);
		orientacaoModelo.setTitulo(orientacaoDto.titulo());
		orientacaoModelo.setTipoOrientacao(orientacaoDto.tipoOrientacao());
		orientacaoModelo.setConteudo(orientacaoDto.conteudo());
		orientacaoModelo.setUsuarioCriador(usuarioCriador);

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

	public void apagarOrientacaoId(OrientacaoDto orientacaoDto) {
		var idOrientacao = pegarIdOrientacao(orientacaoDto);

		repositorioOrientacao.removerOrientacoesPorId(idOrientacao);

	}

	public void removerOrientacaoPorId(String idOrientacao) {
		repositorioOrientacao.removerOrientacaoPorId(idOrientacao);
	}

	public List<OrientacaoDto> pegarOrientacoesPorId(OrientacaoDto orientacaoDto) {
		var listaOrientacao = repositorioOrientacao.pegarOrientacoesPorId(pegarIdOrientacao(orientacaoDto));

		return transformarListaModeloDto(listaOrientacao);
	}

	public int pegarIdCriadorOrientacao(String idOrientacao, IdiomaOrientacao idiomaOrientacao) {
		return repositorioOrientacao.pegarIdCriadorOrientacao(idOrientacao, idiomaOrientacao);
	}

	public boolean verificarOrientacaoExiste(IdiomaOrientacao idiomaOrientacao, String idOrientacao) {
		return !repositorioOrientacao.verificarIdiomaOrientacao(idOrientacao, idiomaOrientacao);
	}
}
