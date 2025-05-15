package service.filtros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.exceptions.orientacao.OrientacaoException;

public class GerenciadorFiltrosOrientacao {
    private Map<TipoFiltro, FiltroOrientacao<?>> filtrosAtivados = new HashMap<>();
    private String palavraBuscada;

    public GerenciadorFiltrosOrientacao() {
        this.filtrosAtivados = new HashMap<>();
    }

    public void adicionarTipoFiltro(TipoFiltro tipoFiltro, FiltroOrientacao filtroOrientacao) {
        this.filtrosAtivados.put(tipoFiltro, filtroOrientacao);
    }

    public FiltroOrientacao pegarTipoFiltro(TipoFiltro tipoFiltro){
        return this.filtrosAtivados.get(tipoFiltro);
    }

    public List<?> pegarFiltrosDoTipo(TipoFiltro tipoFiltro){
        var filtroOrientacao = filtrosAtivados.get(tipoFiltro);
        return filtroOrientacao.getFiltros();
    }

    public void limparFiltros(IdiomaOrientacao idiomaOrientacao) {
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

    public List<String> obterListaDeFiltrosAtivos() {
        List<String> filtrosAtivos = new ArrayList<>();
        IdiomaOrientacao idiomaSessao = SessaoUsuario.pegarIdioma().obterIdiomaOrientacao();

        if (!filtrosAtivados.isEmpty()) {
            for (TipoFiltro tipoFiltro : filtrosAtivados.keySet()) {
                FiltroOrientacao<?> filtro = filtrosAtivados.get(tipoFiltro);
                
                List<String> filtroAtivo = new ArrayList<>();
                
                filtrosAtivos.addAll(filtroAtivo);
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
