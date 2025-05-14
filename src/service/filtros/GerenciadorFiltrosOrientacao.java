package service.filtros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.exceptions.orientacao.OrientacaoException;
import service.formatacao.FormatacaoFiltro;
import service.formatacao.FormatacaoListaOrientacao;

public class GerenciadorFiltrosOrientacao {
	private Map<TipoFiltro,FiltroOrientacao> filtrosAtivados = new HashMap<>();
    private String palavraBuscada;

    public GerenciadorFiltrosOrientacao() {
    	this.filtrosAtivados = new HashMap<>();
    }
    
    public void adicionarFiltro(TipoFiltro tipoFiltro, IdiomaOrientacao idiomasOrientacao) {
    	var filtroOrientacao = filtrosAtivados.get(tipoFiltro);
    	filtroOrientacao.adicionarFiltro(tipoFiltro);
    }
    
    public void limparFiltros(IdiomaOrientacao idiomaOrientacao) {
    	filtrosAtivados.clear();
    	filtrosAtivados.
    }

    public List<OrientacaoDto> aplicarFiltros(List<OrientacaoDto> listaOriginal, OrientacaoService service)
            throws OrientacaoException {
        List<OrientacaoDto> listaFiltrada = aplicaFiltroDefinidos(listaOriginal);

        if (verificarPesquisa()) {
            listaFiltrada = service.pesquisarOrientacao(palavraBuscada, listaFiltrada);
        }

        return listaFiltrada;
    }
    
    public List<List<String>> obterListaDeFiltrosAtivos() {
        List<List<String>> filtrosAtivos = new ArrayList<>();
        IdiomaOrientacao idiomaSessao = SessaoUsuario.pegarIdioma().obterIdiomaOrientacao();

        if (filtroOrientacaoIdioma != null && !filtroOrientacaoIdioma.getIdiomasOrientacoes().isEmpty()) {
            List<String> idiomas = filtroOrientacaoIdioma.getIdiomasOrientacoes().stream()
                .map(i -> i.getNomePorIdioma(idiomaSessao))
                .collect(Collectors.toList());
            filtrosAtivos.add(idiomas);
        }

        if (filtroOrientacaoTipo != null && !filtroOrientacaoTipo.getTiposOrientacao().isEmpty()) {
            List<String> tipos = filtroOrientacaoTipo.getTiposOrientacao().stream()
                .map(t -> t.getNome(idiomaSessao))
                .collect(Collectors.toList());
            filtrosAtivos.add(tipos);
        }

        return filtrosAtivos;
    }


    public boolean verificarPesquisa() {
        return palavraBuscada != null && !palavraBuscada.trim().isEmpty();
    }

    public List<OrientacaoDto> aplicaFiltroDefinidos(List<OrientacaoDto> lista) {
        List<OrientacaoDto> resultado = new ArrayList<>(lista);

        if (filtroOrientacaoIdioma != null && !filtroOrientacaoIdioma.getIdiomasOrientacoes().isEmpty()) {
            resultado = filtroOrientacaoIdioma.aplicarFiltro(resultado);
        }

        if (filtroOrientacaoTipo != null && !filtroOrientacaoTipo.getTiposOrientacao().isEmpty()) {
            resultado = filtroOrientacaoTipo.aplicarFiltro(resultado);
        }

        return resultado;
    }


    public boolean definirFiltroIdioma(IdiomaOrientacao idioma) {
        filtroOrientacaoIdioma.adicionarIdioma(idioma);
        return true;
    }

    public boolean definirFiltroTipo(TipoOrientacao tipo) {
        filtroOrientacaoTipo.adicionarTipo(tipo);
        return true;
    }

    public String getPalavraBuscada() {
        return palavraBuscada;
    }

    public void setPalavraBuscada(String palavraBuscada) {
        this.palavraBuscada = palavraBuscada;
    }

    public void limparTodosOsFiltros() {
        filtroOrientacaoIdioma.limparFiltros();
        filtroOrientacaoTipo.limparFiltros();
    }

    public String formatarFiltrosAtivados() {
        StringBuilder formatado = new StringBuilder();
        int numero = 1;

        if (filtroOrientacaoIdioma != null && !filtroOrientacaoIdioma.getIdiomasOrientacoes().isEmpty()) {
            formatado.append(numero).append("- ").append(filtroOrientacaoIdioma.pegarIdiomas()).append("\n");
            numero++;
        }

        if (filtroOrientacaoTipo != null && !filtroOrientacaoTipo.getTiposOrientacao().isEmpty()) {
            formatado.append(numero).append("- ").append(filtroOrientacaoTipo.pegarTipos()).append("\n");
            numero++;
        }

        return formatado.length() > 0 ? formatado.toString() : "Nenhum filtro ativo.\n";
    }
}
