package service.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import dtos.OrientacaoDto;

public class FiltroOrientacaoTipo implements FiltroOrientacao<TipoOrientacao> {
    private List<TipoOrientacao> tiposOrientacao = new ArrayList<>();

    public FiltroOrientacaoTipo(List<TipoOrientacao> tiposOrientacao) {
        this.tiposOrientacao = tiposOrientacao;
    }

    public FiltroOrientacaoTipo(TipoOrientacao tipoOrientacao) {
        this.tiposOrientacao.add(tipoOrientacao);
    }

    public FiltroOrientacaoTipo() {
    }
    
    @Override
    public String pegarFiltrosEmTexto(IdiomaImplementacao idiomaImplementacao) {
    	StringBuilder filtrosEmTexto = new StringBuilder();
    	int numeroFiltroTipo = 1;
    			
    	for(TipoOrientacao tipoFiltro : tiposOrientacao) {
    		filtrosEmTexto.append(" " + numeroFiltroTipo + " - " + tipoFiltro.getNome(idiomaImplementacao.obterIdiomaOrientacao()));
    		numeroFiltroTipo++;
    	}
    	return filtrosEmTexto.toString();
    }
    
    @Override
    public void apagarFiltro(int index) {
    	tiposOrientacao.remove(index-1);
    }
    
	@Override
	public String pegarTodosFiltrosEmTexto(IdiomaImplementacao idiomaImplementacao) {
    	StringBuilder filtrosEmTexto = new StringBuilder();
    	int numeroFiltroTipo = 1;
    			
    	for(TipoOrientacao idiomaFiltro : TipoOrientacao.values()) {
    		filtrosEmTexto.append(" " + numeroFiltroTipo + " - " + idiomaFiltro.getNome(idiomaImplementacao.obterIdiomaOrientacao()));
    		numeroFiltroTipo++;
    	}
    	return filtrosEmTexto.toString();
	}


    private boolean filtrarPorTipo(OrientacaoDto orientacao) {
        return tiposOrientacao.contains(orientacao.tipoOrientacao());
    }

    public String pegarTipos(IdiomaImplementacao idiomaImplementacao) {
    	var idioma = idiomaImplementacao.obterIdiomaOrientacao();
        return TipoFiltro.TIPO.pegarNome(idioma) + mostrarTipos(idioma);
    }

    public String mostrarTipos(IdiomaOrientacao idiomaOrientacao) {
        StringBuilder formatadoMostrarTipos = new StringBuilder();
        tiposOrientacao.forEach(i -> formatadoMostrarTipos.append(i.getNome(idiomaOrientacao)).append(", "));
        return formatadoMostrarTipos.toString();
    }

    public List<TipoOrientacao> getTiposOrientacao() {
        return tiposOrientacao;
    }

    public void setTiposOrientacao(List<TipoOrientacao> tiposOrientacao) {
        this.tiposOrientacao = tiposOrientacao;
    }

    public void adicionarTipo(TipoOrientacao tipo) {
        if (!tiposOrientacao.contains(tipo)) {
            tiposOrientacao.add(tipo);
        }
    }

    public void limparFiltros() {
        tiposOrientacao.clear();
    }

    @Override
    public List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> listaObjeto) {
        return listaObjeto.stream()
                .filter(this::filtrarPorTipo)
                .collect(Collectors.toList());
    }

    @Override
    public List<TipoOrientacao> pegarFiltro() {
        return new ArrayList<>(tiposOrientacao);
    }

	@Override
	public boolean adicionarFiltro(TipoOrientacao filtro) {
        if (filtro != null && !tiposOrientacao.contains(filtro)) {
            return tiposOrientacao.add(filtro);
        }
		return false;
	}
	
	@Override
	public List<TipoOrientacao> pegarFiltroPossiveis() {
		return TipoOrientacao.listarTipos();
	}


}
