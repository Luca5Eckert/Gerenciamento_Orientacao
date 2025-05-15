package service.filtros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dominio.IdiomaOrientacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.exceptions.orientacao.OrientacaoException;

public class GerenciadorFiltrosOrientacao {
    private Map<TipoFiltro, FiltroOrientacao<?>> filtrosAtivados = new HashMap<>();
    private String palavraBuscada;

    public GerenciadorFiltrosOrientacao() {
        this.filtrosAtivados = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> boolean adicionarFiltro(TipoFiltro tipoFiltro, T valorFiltro) {
        FiltroOrientacao<T> filtro = (FiltroOrientacao<T>) filtrosAtivados.get(tipoFiltro);
        if (filtro != null) {
            filtro.adicionarFiltro(valorFiltro);
        }
		return false;
    }

    public <T> void adicionarTipoFiltro(TipoFiltro tipoFiltro, FiltroOrientacao<IdiomaOrientacao> idioma) {
        filtrosAtivados.put(tipoFiltro, idioma);
    }

    @SuppressWarnings("unchecked")
    public <T> FiltroOrientacao<T> pegarTipoFiltro(TipoFiltro tipoFiltro) {
        return (FiltroOrientacao<T>) filtrosAtivados.get(tipoFiltro);
    }

    public List<?> pegarFiltrosDoTipo(TipoFiltro tipoFiltro) {
        FiltroOrientacao<?> filtroOrientacao = filtrosAtivados.get(tipoFiltro);
        return filtroOrientacao != null ? filtroOrientacao.pegarFiltro() : List.of();
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

        for (Map.Entry<TipoFiltro, FiltroOrientacao<?>> entry : filtrosAtivados.entrySet()) {
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
}
