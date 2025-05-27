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
	private Map<TipoFiltro, FiltroOrientacao<? extends Filtro<?>>> filtrosAtivados = new HashMap<>();
	private String palavraBuscada;

	public GerenciadorFiltrosOrientacao() {
		this.filtrosAtivados = new HashMap<>();
	}

	public boolean adicionarFiltro(TipoFiltro tipoFiltro, String enumConvertido) {
		var filtro = filtrosAtivados.get(tipoFiltro);

		if (filtro == null) {
			filtro = FiltroFactory.criarFiltro(tipoFiltro);
			filtrosAtivados.put(tipoFiltro, filtro);
		}

		return filtro.adicionarFiltro(enumConvertido);
	}

	public void adicionarTipoFiltro(TipoFiltro tipoFiltro, FiltroOrientacao<? extends Filtro<?>> filtro) {
		filtrosAtivados.put(tipoFiltro, filtro);
	}

	public FiltroOrientacao<?> pegarTipoFiltro(TipoFiltro tipoFiltro) {
		return filtrosAtivados.get(tipoFiltro);
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

		if(listaFiltrada.isEmpty()) {
			throw new OrientacaoException();
		}
		return listaFiltrada;
	}

	public List<String> obterListaDeFiltrosAtivos(IdiomaOrientacao idioma) {
		List<String> filtrosAtivos = new ArrayList<>();

		for (Entry<TipoFiltro, FiltroOrientacao<?>> entry : filtrosAtivados.entrySet()) {
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

	public String pegarFiltrosAtivadosEmTexto(TipoFiltro tipoFiltro, IdiomaImplementacao idiomaImplementacao) {
		return filtrosAtivados.get(tipoFiltro).pegarTodosFiltrosEmTexto(idiomaImplementacao);
	}


	public void apagarOrientacao(TipoFiltro tipoFiltro, int index) {
		filtrosAtivados.get(tipoFiltro).apagarFiltro(index - 1);
	}
}
