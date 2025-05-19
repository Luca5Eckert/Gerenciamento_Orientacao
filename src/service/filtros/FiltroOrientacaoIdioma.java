package service.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.IdiomaOrientacao;
import aplication.implementacoes.IdiomaImplementacao;
import aplication.interfaces.TipoMenu;
import dtos.OrientacaoDto;

public class FiltroOrientacaoIdioma implements FiltroOrientacao<IdiomaOrientacao> {
    private List<IdiomaOrientacao> idiomasOrientacoes = new ArrayList<>();

    public FiltroOrientacaoIdioma(List<IdiomaOrientacao> idiomasOrientacoes) {
        this.idiomasOrientacoes = idiomasOrientacoes;
    }
    
    @Override
    public boolean adicionarFiltro(String filtro) {
    	var filtroConvertido = IdiomaOrientacao.valueOf(filtro);
    	if (filtro != null && !idiomasOrientacoes.contains(filtroConvertido)) {
    		return idiomasOrientacoes.add(filtroConvertido);
    	}
    	return false;
    }
    
    @Override
    public List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> listaObjeto) {
    	return listaObjeto.stream()
    			.filter(this::filtrarPorIdioma)
    			.collect(Collectors.toList());
    }
    
    @Override
    public List<IdiomaOrientacao> pegarFiltro() {
    	return new ArrayList<>(idiomasOrientacoes);
    }

    public FiltroOrientacaoIdioma(IdiomaOrientacao idiomaOrientacao) {
        this.idiomasOrientacoes.add(idiomaOrientacao);
    }

    @Override
    public String pegarFiltrosEmTexto(IdiomaImplementacao idiomaImplementacao) {
    	StringBuilder filtrosEmTexto = new StringBuilder();
    	int numeroFiltroIdioma = 1;
    			
    	for(IdiomaOrientacao idiomaFiltro : idiomasOrientacoes) {
    		filtrosEmTexto.append(" " + numeroFiltroIdioma + " - " + idiomaFiltro.getNomePorIdioma(idiomaImplementacao.obterIdiomaOrientacao()));
    		numeroFiltroIdioma++;
    	}
    	return filtrosEmTexto.toString();
    }
    
    @Override
    public void apagarFiltro(int index) {
    	idiomasOrientacoes.remove(index-1);
    }

    @Override
    public String pegarTodosFiltrosEmTexto(IdiomaImplementacao idiomaImplementacao) {
    	StringBuilder filtrosEmTexto = new StringBuilder();
    	int numeroFiltroIdioma = 1;
    	
    	for(IdiomaOrientacao idiomaFiltro : idiomasOrientacoes) {
    		filtrosEmTexto.append(" " + numeroFiltroIdioma + " - " + idiomaFiltro.getNomePorIdioma(idiomaImplementacao.obterIdiomaOrientacao()));
    		numeroFiltroIdioma++;
    	}
    	return filtrosEmTexto.toString();
    }
    
    @Override
    public List<IdiomaOrientacao> pegarFiltroPossiveis() {
    	return IdiomaOrientacao.listarIdiomas();
    }

    public FiltroOrientacaoIdioma() {
    }

    private boolean filtrarPorIdioma(OrientacaoDto orientacao) {
        return idiomasOrientacoes.contains(orientacao.idiomaOrientacao());
    }

    public String pegarIdiomas(IdiomaImplementacao idiomaImplementacao) {
    	var idioma = idiomaImplementacao.obterIdiomaOrientacao();
    	return TipoFiltro.IDIOMA.pegarNome(idioma) + mostrarIdiomas(idioma);
    }

    public String mostrarIdiomas(IdiomaOrientacao idiomaOrientacao) {
        StringBuilder formatadoMostrarIdiomas = new StringBuilder();
        idiomasOrientacoes.stream().forEach(i -> formatadoMostrarIdiomas.append(i.getNomePorIdioma(idiomaOrientacao)).append(", "));
        return formatadoMostrarIdiomas.toString();
    }

    public List<IdiomaOrientacao> getIdiomasOrientacoes() {
        return idiomasOrientacoes;
    }

    public void setIdiomasOrientacoes(List<IdiomaOrientacao> idiomasOrientacoes) {
        this.idiomasOrientacoes = idiomasOrientacoes;
    }

    public void adicionarIdioma(IdiomaOrientacao idioma) {
        if (!idiomasOrientacoes.contains(idioma)) {
            idiomasOrientacoes.add(idioma);
        }
    }

    public void limparFiltros() {
        idiomasOrientacoes.clear();
    }


}
