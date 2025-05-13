package service.filtros;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;
import service.OrientacaoService;
import service.SessaoUsuario;
import service.exceptions.orientacao.OrientacaoException;

public class GerenciadorFiltrosOrientacao {
    private FiltroOrientacaoIdioma filtroOrientacaoIdioma = new FiltroOrientacaoIdioma();
    private FiltroOrientacaoTipo filtroOrientacaoTipo = new FiltroOrientacaoTipo();
    private String palavraBuscada;

    public GerenciadorFiltrosOrientacao(IdiomaOrientacao idiomasOrientacao) {
    	this.filtroOrientacaoIdioma.adicionarIdioma(idiomasOrientacao);
    }
    
    public GerenciadorFiltrosOrientacao(List<IdiomaOrientacao> idiomasOrientacao, List<TipoOrientacao> tiposOrientacao) {
        this.filtroOrientacaoIdioma = new FiltroOrientacaoIdioma(idiomasOrientacao);  
        this.filtroOrientacaoTipo = new FiltroOrientacaoTipo(tiposOrientacao);  
    }
    
    public void definirFitroPadrao(IdiomaOrientacao idiomaOrientacao) {
    	filtroOrientacaoIdioma.getIdiomasOrientacoes().clear();
    	filtroOrientacaoTipo.getTiposOrientacao().clear();
    	filtroOrientacaoIdioma.adicionarIdioma(idiomaOrientacao);
    }

    public List<OrientacaoDto> aplicarFiltros(List<OrientacaoDto> listaOriginal, OrientacaoService service)
            throws OrientacaoException {
        List<OrientacaoDto> listaFiltrada = aplicaFiltroDefinidos(listaOriginal);

        if (verificarPesquisa()) {
            listaFiltrada = service.pesquisarOrientacao(palavraBuscada, listaFiltrada);
        }

        return listaFiltrada;
    }
    
    public String formatarFiltrosAtivadosParaApagar() {
    	IdiomaImplementacao idiomaImplementacao = SessaoUsuario.pegarIdioma();
    	
    	if(!filtroOrientacaoIdioma.getIdiomasOrientacoes().isEmpty()) {
    		System.out.println(idiomaImplementacao.pegar);
    	}
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
