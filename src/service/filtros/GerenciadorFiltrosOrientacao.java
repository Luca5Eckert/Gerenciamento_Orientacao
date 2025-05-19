package service.filtros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Dominio.Filtro;
import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.exceptions.orientacao.OrientacaoException;

public class GerenciadorFiltrosOrientacao {
	private Map<TipoFiltro, FiltroOrientacao<? extends Enum>> filtrosAtivados = new HashMap<>();
	private String palavraBuscada;

	public GerenciadorFiltrosOrientacao() {
		this.filtrosAtivados = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	public <T extends Enum<?>> boolean adicionarFiltro(TipoFiltro tipoFiltro, T enumConvertido) {
		FiltroOrientacao<T> filtro = (FiltroOrientacao<T>) filtrosAtivados.get(tipoFiltro);

		if (filtro == null) {
			filtro = (FiltroOrientacao<T>) FiltroFactory.criarFiltro(tipoFiltro);
			filtrosAtivados.put(tipoFiltro, filtro);
		}

		filtro.adicionarFiltro(enumConvertido);
		return true;
	}

	public <T extends Enum<T>> void adicionarTipoFiltro(TipoFiltro tipoFiltro, FiltroOrientacao filtro) {
	    filtrosAtivados.put(tipoFiltro, filtro);
	}

	@SuppressWarnings("unchecked")
	public <T extends Enum> FiltroOrientacao<T> pegarTipoFiltro(TipoFiltro tipoFiltro) {
		return (FiltroOrientacao<T>) filtrosAtivados.get(tipoFiltro);
	}

	public List<?> pegarFiltrosDoTipo(TipoFiltro tipoFiltro) {
		FiltroOrientacao<?> filtroOrientacao = filtrosAtivados.get(tipoFiltro);
		return filtroOrientacao != null ? filtroOrientacao.pegarFiltro() : List.of();
	}

	public String pegarFiltrosTipoEmTexto(TipoFiltro tipo, IdiomaImplementacao idiomaImplementacao) {
		return filtrosAtivados.get(tipo).pegarFiltrosEmTexto(idiomaImplementacao);
	}

	public void limparFiltros() {
		filtrosAtivados.clear();
	}

	public List<OrientacaoDto> aplicarFiltros(List<OrientacaoDto> listaOriginal, OrientacaoService service)
			throws OrientacaoException {
		List<OrientacaoDto> listaFiltrada = aplicaFiltroDefinidos(listaOriginal);

		if (verificarPesquisa()) {
			listaFiltrada = service.pesquisarOrientacao(palavraBuscada, listaFiltrada);
		}

		return listaFiltrada;
	}

	public List<String> obterListaDeFiltrosAtivos(IdiomaOrientacao idioma) {
		List<String> filtrosAtivos = new ArrayList<>();

		for (Entry<TipoFiltro, FiltroOrientacao<? extends Enum>> entry : filtrosAtivados.entrySet()) {
			TipoFiltro tipo = entry.getKey();
			FiltroOrientacao<?> filtro = entry.getValue();

			String descricao = tipo.name() + ": ";

			List<?> valores = filtro.pegarFiltro();
			if (!valores.isEmpty()) {
				descricao += valores.toString();
				filtrosAtivos.add(descricao);
			}
		}

		return filtrosAtivos;
	}

	public boolean verificarPesquisa() {
		return palavraBuscada != null && !palavraBuscada.trim().isEmpty();
	}

	public List<OrientacaoDto> aplicaFiltroDefinidos(List<OrientacaoDto> lista) {
		List<OrientacaoDto> resultado = new ArrayList<>(lista);

		for (FiltroOrientacao<?> filtro : filtrosAtivados.values()) {
			if (filtro != null) {
				resultado = filtro.aplicarFiltro(resultado);
			}
		}

		return resultado;
	}

	public String getPalavraBuscada() {
		return palavraBuscada;
	}

	public void setPalavraBuscada(String palavraBuscada) {
		this.palavraBuscada = palavraBuscada;
	}

	public List<TipoFiltro> pegarTiposDeFiltros() {
		List<TipoFiltro> listaTiposFiltro = new ArrayList<>();

		for (TipoFiltro tipoFiltro : filtrosAtivados.keySet()) {
			listaTiposFiltro.add(tipoFiltro);
		}

		return listaTiposFiltro;
	}

	public String pegarFiltrosPossiveisEmTexto(TipoFiltro tipoFiltro, IdiomaImplementacao idiomaImplementacao) {
		return filtrosAtivados.get(tipoFiltro).pegarTodosFiltrosEmTexto(idiomaImplementacao);
	}

	public List<Enum> pegarFiltrosPossiveis(TipoFiltro tipoFiltro) {
		return (List<Enum>) filtrosAtivados.get(tipoFiltro).pegarFiltroPossiveis();
	}

	public Enum pegarFiltroPossivel(TipoFiltro tipoFiltro, int index) {
		return pegarFiltrosPossiveis(tipoFiltro).get(index);
	}
	
	public void apagarOrientacao(TipoFiltro tipoFiltro, int index) {
		filtrosAtivados.get(tipoFiltro).apagarFiltro(index-1);
	}
}
