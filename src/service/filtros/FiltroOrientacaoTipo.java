package service.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.IdiomaOrientacao;
import Dominio.TipoOrientacao;
import dtos.OrientacaoDto;
import service.SessaoUsuario;

public class FiltroOrientacaoTipo implements FiltroOrientacao {
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
    public List<OrientacaoDto> aplicarFiltro(List<OrientacaoDto> orientacoes) {
        return orientacoes.stream()
                .filter(this::filtrarPorTipo)
                .collect(Collectors.toList());
    }

    private boolean filtrarPorTipo(OrientacaoDto orientacao) {
        return tiposOrientacao.contains(orientacao.tipoOrientacao());
    }

    public String pegarTipos() {
    	var idioma = SessaoUsuario.pegarIdioma();
        return idioma.pegarFiltroTipo() + mostrarTipos(idioma.obterIdiomaOrientacao());
    }
    
    public String mostrarTipos(IdiomaOrientacao idiomaOrientacao) {
    	StringBuilder formatadoMostrarIdiomas = new StringBuilder();
    	tiposOrientacao.stream().forEach(i -> formatadoMostrarIdiomas.append(i.getNome(idiomaOrientacao) + ", "));
    	return formatadoMostrarIdiomas.toString();
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
}
